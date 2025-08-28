import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';

// 인증이 필요한 경로 패턴
const protectedRoutes = ['/dashboard', '/dashboard/:path*'];

// 인증 필요 없는 경로 패턴
const publicRoutes = ['/', '/auth/login', '/auth/register'];

export function middleware(request: NextRequest) {
    const { pathname } = request.nextUrl;

    // 사용자 인증 상태 확인 (여기서는 쿠키를 사용)
    const isAuthenticated = request.cookies.has('auth-token');

    // 인증된 사용자가 로그인/회원가입 페이지 접근 시 대시보드로 리다이렉트
    if (isAuthenticated && (pathname === '/auth/login' || pathname === '/auth/register')) {
        return NextResponse.redirect(new URL('/dashboard', request.url));
    }

    // 인증되지 않은 사용자가 보호된 경로 접근 시 로그인 페이지로 리다이렉트
    const isProtectedRoute = protectedRoutes.some(route => {
        if (route.includes(':path*')) {
            const basePath = route.replace(':path*', '');
            return pathname.startsWith(basePath);
        }
        return pathname === route;
    });

    if (!isAuthenticated && isProtectedRoute) {
        return NextResponse.redirect(new URL('/auth/login', request.url));
    }

    return NextResponse.next();
}

// 미들웨어가 실행될 경로 지정
export const config = {
    matcher: [
        '/((?!api|_next/static|_next/image|favicon.ico|.*\\.svg).*)',
    ],
};