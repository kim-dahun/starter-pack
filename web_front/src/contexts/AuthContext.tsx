'use client';

import { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import { useRouter, usePathname } from 'next/navigation';
import api from "@/util/axios";
import {CommonResponse, HttpResponse} from "@/common/model/httpModel";
import {api_login, api_logout} from "@/api/userApi";
interface User {
    userId: string;
    userName: string;
    email: string;
    phone: string;
    comCd: string;
    role: string;

}

export interface UserResponse extends CommonResponse {
    data : User;
}

interface AuthContextType {
    user: User | null;
    isLoading: boolean;
    login: (id: string, password: string, comCd : string) => Promise<void>;
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
        console.log('Auth Provider!');
        setIsLoading(false);
    }, []);

    // 로그인 함수
    const login = async (id: string, password: string, comCd : string) => {
        setIsLoading(true);

        if(id && password && comCd) {
            try {
                // 실제로는 백엔드 API 호출이 필요
                const _result = await api_login({ userId : id, userPassword : password, comCd : comCd });
                if(_result.responseCode === 200){
                    const user:User = _result.data;
                    setUser(user);
                    sessionStorage.setItem('userId',user.userId);
                    sessionStorage.setItem('email',user.email);
                    sessionStorage.setItem('comCd',user.comCd);
                    sessionStorage.setItem('userName',user.userName);
                    router.push('/dashboard');
                } else {
                    console.log('Error is occured By Call Api');
                }
            } catch (error) {
                console.error('Login failed:', error);
                throw error;
            } finally {
                setIsLoading(false);
            }
        } else {
            throw new Error('이메일과 비밀번호를 입력해주세요.');
        }

    };

    // 로그아웃 함수
    const logout = async () => {
        try {
            // 서버에 로그아웃 요청을 보내 쿠키 삭제
            await api_logout({});
        } catch (error) {
            console.error('Logout API failed:', error);
        } finally {
            // 세션스토리지 정리
            sessionStorage.removeItem('userId');
            sessionStorage.removeItem('email');
            sessionStorage.removeItem('comCd');
            sessionStorage.removeItem('userName');

            // 상태 초기화
            setUser(null);

            // 리다이렉트
            router.push('/auth/login');
        }
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