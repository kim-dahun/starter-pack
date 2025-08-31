'use client';

import { useEffect } from 'react';
import { useRouter, usePathname } from 'next/navigation';
import { useAuth } from '@/contexts/AuthContext';

// 인증이 필요한 경로 패턴 (미들웨어와 동일하게 유지)
const protectedRoutes = ['/dashboard', '/dashboard/:path*'];
const publicRoutes = ['/', '/auth/login', '/auth/register'];

export function NavigationGuard({ children }: { children: React.ReactNode }) {
    const router = useRouter();
    const pathname = usePathname();
    const { isAuthenticated } = useAuth(); // 인증 컨텍스트에서 상태 가져오기

    useEffect(() => {
        // 1. 뒤로가기/앞으로가기 감지 함수
        const handlePopState = (event: PopStateEvent) => {

            // 보호된 경로인지 확인
            const isProtectedRoute = protectedRoutes.some(route => {
                if (route.includes(':path*')) {
                    const basePath = route.replace(':path*', '');
                    return pathname.startsWith(basePath);
                }
                return pathname === route;
            });

            // 인증되지 않은 사용자가 보호된 경로에 접근하려고 할 때
            if (!isAuthenticated && isProtectedRoute) {
                // 이벤트 기본 동작 방지는 불가능하지만, 즉시 다른 곳으로 리다이렉트
                router.replace('/auth/login');
            }

            // 인증된 사용자가 로그인/회원가입 페이지로 가려고 할 때
            if (isAuthenticated && publicRoutes.includes(pathname)) {
                router.replace('/dashboard');
            }
        };

        // 3. 뒤로가기 방지를 위한 히스토리 조작 (더 강력한 방법)
        const preventBackButton = () => {
            window.history.pushState(null, "", window.location.pathname);
            window.onpopstate = function() {
                window.history.pushState(null, "", window.location.pathname);
            };
        };

        // 보호된 경로에서만 뒤로가기 방지 활성화
        const isCurrentlyProtected = protectedRoutes.some(route => {
            if (route.includes(':path*')) {
                return pathname.startsWith(route.replace(':path*', ''));
            }
            return pathname === route;
        });

        if (isCurrentlyProtected) {
            preventBackButton();
        }

        // 이벤트 리스너 등록
        window.addEventListener('popstate', handlePopState);


        // 정리 함수
        return () => {
            window.removeEventListener('popstate', handlePopState);
        };
    }, [isAuthenticated, pathname, router]);

    return <>{children}</>;
}