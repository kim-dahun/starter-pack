import Link from 'next/link';
import Image from 'next/image';

export default function Home() {
    return (
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-50 dark:bg-gray-900 text-center p-4">
            <h1 className="text-4xl font-bold mb-6">My App</h1>
            <p className="text-xl mb-8 max-w-xl">
                Next.js로 구현된 대시보드 애플리케이션에 오신 것을 환영합니다
            </p>
            <div className="flex gap-4">
                <Link
                    href="/auth/login"
                    className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-md font-medium"
                >
                    로그인
                </Link>
                <Link
                    href="/auth/register"
                    className="bg-gray-200 hover:bg-gray-300 text-gray-800 px-6 py-3 rounded-md font-medium"
                >
                    회원가입
                </Link>
                <Link
                    href="/dashboard"
                    className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-md font-medium"
                >
                    대시보드로 이동
                </Link>
            </div>
        </div>
    );
}