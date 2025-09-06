import type {NextRequest} from 'next/server';
import {NextResponse} from 'next/server';
import {api_logout} from "@/api/userApi";

// 인증이 필요한 경로 패턴
// const protectedRoutes = ['/dashboard', '/dashboard/:path*'];

// 인증 필요 없는 경로 패턴
const publicRoutes = ['/', '/auth/login', '/auth/register'];


export async function middleware(request: NextRequest) {

    const createRedirectResponse = (pathname: string) => {
        // 리다이렉트 응답 생성
        const response = NextResponse.redirect(new URL(pathname, request.url));

        response.headers.set('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
        response.headers.set('Pragma', 'no-cache');
        response.headers.set('Expires', '0');
        response.headers.set('Clear-Site-Data', '"cache", "cookies", "storage"');

        // 쿠키 삭제
        response.cookies.set('access_token', '', {
            expires: new Date(0),
            path: '/'
        });
        response.cookies.set('refresh_token', '', {
            expires: new Date(0),
            path: '/'
        });

        return response;
    }

    const sessionStorage = window.sessionStorage;

    const menuList = sessionStorage.getItem('menuList') ? JSON.parse(sessionStorage.getItem('menuList') as string) : [];

    const { pathname } = request.nextUrl;
    // 사용자 인증 상태 확인 (여기서는 쿠키를 사용)
    const isAuthenticated = request.cookies.has('access_token');
    // 인증된 사용자가 로그인/회원가입 페이지 접근 시 로그아웃 처리
    if (isAuthenticated && (publicRoutes.includes(pathname))) {
        const response = createRedirectResponse(pathname);
        try {
            const _res = await api_logout({});
            if(_res.responseCode === 200){
                return response;
            }
        } catch (e) {
            response.headers.set('Cache-Control', 'no-store, no-cache, must-revalidate');
            response.headers.set('Clear-Site-Data', '"cache"');
            return response;
        }
    }

    // 인증되지 않은 사용자가 보호된 경로 접근 시 로그인 페이지로 리다이렉트
    const isProtectedRoute = !publicRoutes.includes(pathname) && !menuList.includes(pathname);

    if (!isAuthenticated && isProtectedRoute) {
        return createRedirectResponse('/auth/login');
    }

    return NextResponse.next();
}

// 미들웨어가 실행될 경로 지정
export const config = {
    matcher: [
        '/((?!api|_next/static|_next/image|favicon.ico|.*\\.svg).*)',
    ],
};