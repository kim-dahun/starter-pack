'use client';

import { useState } from 'react';
import { useAuth } from '@/contexts/AuthContext';

export default function ProfilePage() {
    const { user } = useAuth();
    const [name, setName] = useState(user?.userName || '');
    const [email, setEmail] = useState(user?.email || '');
    const [phone, setPhone] = useState('010-1234-5678');
    const [position, setPosition] = useState('개발자');
    const [isSaving, setIsSaving] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setIsSaving(true);
        setSuccessMessage('');

        // 실제 구현에서는 API 호출
        setTimeout(() => {
            setSuccessMessage('프로필이 성공적으로 업데이트되었습니다.');
            setIsSaving(false);
        }, 1000);
    };

    return (
        <div>
            <h1 className="text-2xl font-bold mb-6">프로필</h1>

            {successMessage && (
                <div className="mb-6 bg-green-50 text-green-700 p-3 rounded-md">
                    {successMessage}
                </div>
            )}

            <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
                <form onSubmit={handleSubmit}>
                    <div className="flex flex-col md:flex-row gap-6">
                        <div className="md:w-1/4 flex flex-col items-center">
                            <div className="w-32 h-32 bg-gray-300 dark:bg-gray-700 rounded-full mb-4 overflow-hidden flex items-center justify-center">
                                {user && (
                                    <span className="text-4xl text-gray-600 dark:text-gray-300">
                                        {user.userName.charAt(0).toUpperCase()}
                                    </span>
                                )}
                            </div>
                            <button
                                type="button"
                                className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:opacity-50"
                                disabled={isSaving}
                            >
                                이미지 변경
                            </button>
                        </div>

                        <div className="md:w-3/4">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                        이름
                                    </label>
                                    <input
                                        type="text"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        disabled={isSaving}
                                    />
                                </div>

                                <div>
                                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                        이메일
                                    </label>
                                    <input
                                        type="email"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        disabled={isSaving}
                                    />
                                </div>

                                <div>
                                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                        전화번호
                                    </label>
                                    <input
                                        type="tel"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={phone}
                                        onChange={(e) => setPhone(e.target.value)}
                                        disabled={isSaving}
                                    />
                                </div>

                                <div>
                                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                        직책
                                    </label>
                                    <input
                                        type="text"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={position}
                                        onChange={(e) => setPosition(e.target.value)}
                                        disabled={isSaving}
                                    />
                                </div>
                            </div>

                            <div className="mt-6">
                                <button
                                    type="submit"
                                    className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:opacity-50"
                                    disabled={isSaving}
                                >
                                    {isSaving ? '저장 중...' : '저장하기'}
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}