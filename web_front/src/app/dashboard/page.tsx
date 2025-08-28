'use client';

import { useAuth } from '@/contexts/AuthContext';

export default function DashboardPage() {
    const { user } = useAuth();

    return (
        <div>
            <h1 className="text-2xl font-bold mb-6">대시보드</h1>

            {user && (
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6 mb-6">
                    <h2 className="text-lg font-semibold mb-4">
                        안녕하세요, {user.name}님!
                    </h2>
                    <p className="text-gray-600 dark:text-gray-300">
                        오늘도 좋은 하루 되세요.
                    </p>
                </div>
            )}

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
                    <h2 className="text-lg font-semibold mb-4">활동 요약</h2>
                    <p className="text-gray-600 dark:text-gray-300">
                        이번 주 활동 내역을 확인해보세요.
                    </p>
                    <div className="mt-4 flex justify-end">
                        <button className="text-blue-500 hover:text-blue-700 text-sm">
                            자세히 보기 →
                        </button>
                    </div>
                </div>

                <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
                    <h2 className="text-lg font-semibold mb-4">최근 알림</h2>
                    <ul className="space-y-2 text-gray-600 dark:text-gray-300 text-sm">
                        <li className="pb-2 border-b border-gray-100 dark:border-gray-700">
                            새로운 메시지가 도착했습니다.
                            <div className="text-xs text-gray-500">2시간 전</div>
                        </li>
                        <li className="pb-2 border-b border-gray-100 dark:border-gray-700">
                            프로필 정보 업데이트가 필요합니다.
                            <div className="text-xs text-gray-500">어제</div>
                        </li>
                    </ul>
                    <div className="mt-4 flex justify-end">
                        <button className="text-blue-500 hover:text-blue-700 text-sm">
                            모든 알림 보기 →
                        </button>
                    </div>
                </div>

                <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
                    <h2 className="text-lg font-semibold mb-4">빠른 액세스</h2>
                    <div className="grid grid-cols-2 gap-3">
                        <button className="p-3 text-sm text-center border border-gray-200 dark:border-gray-700 rounded-md hover:bg-gray-50 dark:hover:bg-gray-700">
                            문서 만들기
                        </button>
                        <button className="p-3 text-sm text-center border border-gray-200 dark:border-gray-700 rounded-md hover:bg-gray-50 dark:hover:bg-gray-700">
                            보고서 확인
                        </button>
                        <button className="p-3 text-sm text-center border border-gray-200 dark:border-gray-700 rounded-md hover:bg-gray-50 dark:hover:bg-gray-700">
                            설정 변경
                        </button>
                        <button className="p-3 text-sm text-center border border-gray-200 dark:border-gray-700 rounded-md hover:bg-gray-50 dark:hover:bg-gray-700">
                            도움말
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}