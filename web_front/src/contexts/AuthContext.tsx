'use client';

import { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import { useRouter, usePathname } from 'next/navigation';
import { getCookie, setCookie, deleteCookie } from 'cookies-next';

interface User {
    id: string;
    name: string;
    email: string;
    role: string;
}

interface AuthContextType {
    user: User | null;
    isLoading: boolean;
    login: (email: string, password: string) => Promise<void>;
    logout: () => void;
    isAuthenticated: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
    const [user, setUser] = useState<User | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const router = useRouter();
    const pathname = usePathname();

    // 초기 로드 시 인증 상태 확인
    useEffect(() => {
        const checkAuth = async () => {
            try {
                const token = getCookie('auth-token');

                if (token) {
                    // 실제로는 토큰을 검증하고 사용자 정보를 가져오는 API 호출이 필요
                    // 여기서는 간단한 예시로 대체
                    setUser({
                        id: '1',
                        name: '홍길동',
                        email: 'user@example.com',
                        role: 'user'
                    });
                }
            } catch (error) {
                console.error('Authentication check failed:', error);
                deleteCookie('auth-token');
                setUser(null);
            } finally {
                setIsLoading(false);
            }
        };

        checkAuth();
    }, []);

    // 로그인 함수
    const login = async (email: string, password: string) => {
        setIsLoading(true);

        try {
            // 실제로는 백엔드 API 호출이 필요
            // 여기서는 간단한 검증만 수행
            if (email && password) {
                // 성공 시 쿠키 설정 및 사용자 정보 설정
                setCookie('auth-token', 'sample-token-value', {
                    maxAge: 60 * 60 * 24 * 7, // 7일간 유효
                    path: '/',
                    secure: process.env.NODE_ENV === 'production',
                    sameSite: 'strict'
                });

                setUser({
                    id: '1',
                    name: '홍길동',
                    email,
                    role: 'user'
                });

                router.push('/dashboard');
            } else {
                throw new Error('이메일과 비밀번호를 입력해주세요.');
            }
        } catch (error) {
            console.error('Login failed:', error);
            throw error;
        } finally {
            setIsLoading(false);
        }
    };

    // 로그아웃 함수
    const logout = () => {
        deleteCookie('auth-token');
        setUser(null);
        router.push('/auth/login');
    };

    return (
        <AuthContext.Provider
            value={{
                user,
                isLoading,
                login,
                logout,
                isAuthenticated: !!user
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

// 커스텀 훅
export function useAuth() {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
}