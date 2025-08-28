'use client';

import { useState, useEffect } from 'react';
import { useAuth } from '@/contexts/AuthContext';

type SettingTab = 'general' | 'notifications' | 'security' | 'appearance';
type Theme = 'light' | 'dark' | 'system';
type ColorScheme = 'blue' | 'purple' | 'green' | 'red';
type FontSize = 'sm' | 'md' | 'lg' | 'xl';
type NotificationFrequency = 'immediately' | 'daily' | 'weekly' | 'never';

export default function SettingsPage() {
    const { user } = useAuth();
    const [activeTab, setActiveTab] = useState<SettingTab>('general');
    const [isSaving, setIsSaving] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');

    // 일반 설정 상태
    const [autoLogin, setAutoLogin] = useState(true);
    const [language, setLanguage] = useState('ko');
    const [timezone, setTimezone] = useState('asia/seoul');

    // 알림 설정 상태
    const [emailNotifications, setEmailNotifications] = useState(true);
    const [pushNotifications, setPushNotifications] = useState(true);
    const [smsNotifications, setSmsNotifications] = useState(false);
    const [notificationFrequency, setNotificationFrequency] = useState<NotificationFrequency>('immediately');

    // 보안 설정 상태
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [twoFactorEnabled, setTwoFactorEnabled] = useState(false);
    const [passwordError, setPasswordError] = useState('');

    // 외관 설정 상태
    const [theme, setTheme] = useState<Theme>('light');
    const [colorScheme, setColorScheme] = useState<ColorScheme>('blue');
    const [fontSize, setFontSize] = useState<FontSize>('md');
    const [animations, setAnimations] = useState(true);

    // 연결된 기기 상태
    const [devices, setDevices] = useState([
        { id: '1', name: 'MacBook Pro - Chrome', lastAccess: '오늘, 10:30' },
        { id: '2', name: 'iPhone 14 - Safari', lastAccess: '어제, 19:45' }
    ]);

    // 설정 초기화 (실제로는 API에서 사용자 설정을 로드)
    useEffect(() => {
        // 사용자가 로그인한 경우 설정 로드
        if (user) {
            // 실제 구현에서는 API 호출하여 설정 로드
            console.log('사용자 설정 로드:', user.email);
        }
    }, [user]);

    // 일반 설정 저장
    const saveGeneralSettings = () => {
        setIsSaving(true);
        setSuccessMessage('');

        // 실제 구현에서는 API 호출
        setTimeout(() => {
            console.log('일반 설정 저장:', { autoLogin, language, timezone });
            setSuccessMessage('설정이 성공적으로 저장되었습니다.');
            setIsSaving(false);
        }, 800);
    };

    // 알림 설정 저장
    const saveNotificationSettings = () => {
        setIsSaving(true);
        setSuccessMessage('');

        // 실제 구현에서는 API 호출
        setTimeout(() => {
            console.log('알림 설정 저장:', {
                emailNotifications,
                pushNotifications,
                smsNotifications,
                notificationFrequency
            });
            setSuccessMessage('알림 설정이 성공적으로 저장되었습니다.');
            setIsSaving(false);
        }, 800);
    };

    // 비밀번호 변경
    const changePassword = () => {
        setIsSaving(true);
        setSuccessMessage('');
        setPasswordError('');

        // 간단한 유효성 검사
        if (newPassword !== confirmPassword) {
            setPasswordError('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
            setIsSaving(false);
            return;
        }

        if (newPassword.length < 8) {
            setPasswordError('비밀번호는 최소 8자 이상이어야 합니다.');
            setIsSaving(false);
            return;
        }

        // 실제 구현에서는 API 호출
        setTimeout(() => {
            console.log('비밀번호 변경:', { currentPassword, newPassword });
            setSuccessMessage('비밀번호가 성공적으로 변경되었습니다.');
            setCurrentPassword('');
            setNewPassword('');
            setConfirmPassword('');
            setIsSaving(false);
        }, 800);
    };

    // 2단계 인증 토글
    const toggleTwoFactor = () => {
        setTwoFactorEnabled(!twoFactorEnabled);

        // 실제 구현에서는 API 호출
        console.log('2단계 인증 설정:', !twoFactorEnabled);
    };

    // 기기 로그아웃
    const logoutDevice = (deviceId: string) => {
        // 실제 구현에서는 API 호출
        console.log('기기 로그아웃:', deviceId);

        // 기기 목록에서 제거
        setDevices(devices.filter(device => device.id !== deviceId));
    };

    // 외관 설정 저장
    const saveAppearanceSettings = () => {
        setIsSaving(true);
        setSuccessMessage('');

        // 테마 적용 (실제로는 더 복잡한 구현 필요)
        document.documentElement.classList.remove('dark');
        if (theme === 'dark' || (theme === 'system' && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
            document.documentElement.classList.add('dark');
        }

        // 실제 구현에서는 API 호출
        setTimeout(() => {
            console.log('외관 설정 저장:', { theme, colorScheme, fontSize, animations });
            setSuccessMessage('외관 설정이 성공적으로 저장되었습니다.');
            setIsSaving(false);
        }, 800);
    };

    return (
        <div>
            <h1 className="text-2xl font-bold mb-6">설정</h1>

            {successMessage && (
                <div className="mb-6 bg-green-50 text-green-700 p-3 rounded-md">
                    {successMessage}
                </div>
            )}

            <div className="bg-white dark:bg-gray-800 rounded-lg shadow">
                <div className="border-b border-gray-200 dark:border-gray-700">
                    <nav className="flex overflow-x-auto">
                        <button
                            onClick={() => setActiveTab('general')}
                            className={`px-4 py-3 text-sm font-medium whitespace-nowrap ${
                                activeTab === 'general'
                                    ? 'border-b-2 border-blue-500 text-blue-600 dark:text-blue-500'
                                    : 'text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-300'
                            }`}
                        >
                            일반
                        </button>
                        <button
                            onClick={() => setActiveTab('notifications')}
                            className={`px-4 py-3 text-sm font-medium whitespace-nowrap ${
                                activeTab === 'notifications'
                                    ? 'border-b-2 border-blue-500 text-blue-600 dark:text-blue-500'
                                    : 'text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-300'
                            }`}
                        >
                            알림
                        </button>
                        <button
                            onClick={() => setActiveTab('security')}
                            className={`px-4 py-3 text-sm font-medium whitespace-nowrap ${
                                activeTab === 'security'
                                    ? 'border-b-2 border-blue-500 text-blue-600 dark:text-blue-500'
                                    : 'text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-300'
                            }`}
                        >
                            보안
                        </button>
                        <button
                            onClick={() => setActiveTab('appearance')}
                            className={`px-4 py-3 text-sm font-medium whitespace-nowrap ${
                                activeTab === 'appearance'
                                    ? 'border-b-2 border-blue-500 text-blue-600 dark:text-blue-500'
                                    : 'text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-300'
                            }`}
                        >
                            외관
                        </button>
                    </nav>
                </div>

                <div className="p-6">
                    {activeTab === 'general' && (
                        <div className="space-y-6">
                            <h2 className="text-lg font-medium">일반 설정</h2>

                            <div>
                                <label className="flex items-center space-x-2">
                                    <input
                                        type="checkbox"
                                        className="w-4 h-4 rounded border-gray-300"
                                        checked={autoLogin}
                                        onChange={(e) => setAutoLogin(e.target.checked)}
                                    />
                                    <span className="text-sm text-gray-700 dark:text-gray-300">자동 로그인 활성화</span>
                                </label>
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    기본 언어
                                </label>
                                <select
                                    className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                    value={language}
                                    onChange={(e) => setLanguage(e.target.value)}
                                >
                                    <option value="ko">한국어</option>
                                    <option value="en">English</option>
                                    <option value="ja">日本語</option>
                                    <option value="zh">中文</option>
                                </select>
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    시간대
                                </label>
                                <select
                                    className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                    value={timezone}
                                    onChange={(e) => setTimezone(e.target.value)}
                                >
                                    <option value="asia/seoul">(GMT+09:00) 서울, 도쿄, 오사카</option>
                                    <option value="america/new_york">(GMT-05:00) 뉴욕</option>
                                    <option value="europe/london">(GMT+00:00) 런던</option>
                                    <option value="asia/shanghai">(GMT+08:00) 베이징, 상하이</option>
                                </select>
                            </div>
                        </div>
                    )}

                    {activeTab === 'notifications' && (
                        <div className="space-y-6">
                            <h2 className="text-lg font-medium">알림 설정</h2>

                            <div className="space-y-4">
                                <div>
                                    <label className="flex items-center justify-between">
                                        <span className="text-sm text-gray-700 dark:text-gray-300">이메일 알림</span>
                                        <div className="relative inline-block w-10 mr-2 align-middle select-none">
                                            <input
                                                type="checkbox"
                                                id="email-notifications"
                                                checked={emailNotifications}
                                                onChange={(e) => setEmailNotifications(e.target.checked)}
                                                className="sr-only"
                                            />
                                            <label
                                                htmlFor="email-notifications"
                                                className={`block h-6 overflow-hidden rounded-full cursor-pointer ${
                                                    emailNotifications ? 'bg-blue-500' : 'bg-gray-300 dark:bg-gray-700'
                                                }`}
                                            >
                                                <span
                                                    className={`block h-6 w-6 rounded-full bg-white transform transition-transform duration-200 ease-in ${
                                                        emailNotifications ? 'translate-x-4' : 'translate-x-0'
                                                    }`}
                                                />
                                            </label>
                                        </div>
                                    </label>
                                </div>

                                <div>
                                    <label className="flex items-center justify-between">
                                        <span className="text-sm text-gray-700 dark:text-gray-300">푸시 알림</span>
                                        <div className="relative inline-block w-10 mr-2 align-middle select-none">
                                            <input
                                                type="checkbox"
                                                id="push-notifications"
                                                checked={pushNotifications}
                                                onChange={(e) => setPushNotifications(e.target.checked)}
                                                className="sr-only"
                                            />
                                            <label
                                                htmlFor="push-notifications"
                                                className={`block h-6 overflow-hidden rounded-full cursor-pointer ${
                                                    pushNotifications ? 'bg-blue-500' : 'bg-gray-300 dark:bg-gray-700'
                                                }`}
                                            >
                                                <span
                                                    className={`block h-6 w-6 rounded-full bg-white transform transition-transform duration-200 ease-in ${
                                                        pushNotifications ? 'translate-x-4' : 'translate-x-0'
                                                    }`}
                                                />
                                            </label>
                                        </div>
                                    </label>
                                </div>

                                <div>
                                    <label className="flex items-center justify-between">
                                        <span className="text-sm text-gray-700 dark:text-gray-300">SMS 알림</span>
                                        <div className="relative inline-block w-10 mr-2 align-middle select-none">
                                            <input
                                                type="checkbox"
                                                id="sms-notifications"
                                                checked={smsNotifications}
                                                onChange={(e) => setSmsNotifications(e.target.checked)}
                                                className="sr-only"
                                            />
                                            <label
                                                htmlFor="sms-notifications"
                                                className={`block h-6 overflow-hidden rounded-full cursor-pointer ${
                                                    smsNotifications ? 'bg-blue-500' : 'bg-gray-300 dark:bg-gray-700'
                                                }`}
                                            >
                                                <span
                                                    className={`block h-6 w-6 rounded-full bg-white transform transition-transform duration-200 ease-in ${
                                                        smsNotifications ? 'translate-x-4' : 'translate-x-0'
                                                    }`}
                                                />
                                            </label>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    알림 빈도
                                </label>
                                <select
                                    className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                    value={notificationFrequency}
                                    onChange={(e) => setNotificationFrequency(e.target.value as NotificationFrequency)}
                                >
                                    <option value="immediately">즉시</option>
                                    <option value="daily">하루에 한 번</option>
                                    <option value="weekly">일주일에 한 번</option>
                                    <option value="never">안 받음</option>
                                </select>
                            </div>
                        </div>
                    )}

                    {activeTab === 'security' && (
                        <div className="space-y-6">
                            <h2 className="text-lg font-medium">보안 설정</h2>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    비밀번호 변경
                                </label>
                                <div className="space-y-3">
                                    {passwordError && (
                                        <div className="text-sm text-red-500">
                                            {passwordError}
                                        </div>
                                    )}
                                    <input
                                        type="password"
                                        placeholder="현재 비밀번호"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={currentPassword}
                                        onChange={(e) => setCurrentPassword(e.target.value)}
                                    />
                                    <input
                                        type="password"
                                        placeholder="새 비밀번호"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={newPassword}
                                        onChange={(e) => setNewPassword(e.target.value)}
                                    />
                                    <input
                                        type="password"
                                        placeholder="새 비밀번호 확인"
                                        className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                        value={confirmPassword}
                                        onChange={(e) => setConfirmPassword(e.target.value)}
                                    />
                                    <div>
                                        <button
                                            type="button"
                                            onClick={changePassword}
                                            disabled={isSaving || !currentPassword || !newPassword || !confirmPassword}
                                            className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:opacity-50 disabled:cursor-not-allowed"
                                        >
                                            {isSaving ? '변경 중...' : '비밀번호 변경'}
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <label className="flex items-center space-x-2">
                                    <input
                                        type="checkbox"
                                        className="w-4 h-4 rounded border-gray-300"
                                        checked={twoFactorEnabled}
                                        onChange={toggleTwoFactor}
                                    />
                                    <span className="text-sm text-gray-700 dark:text-gray-300">2단계 인증 활성화</span>
                                </label>
                            </div>

                            <div>
                                <h3 className="text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">연결된 기기</h3>
                                <div className="space-y-2">
                                    {devices.map(device => (
                                        <div
                                            key={device.id}
                                            className="p-3 bg-gray-50 dark:bg-gray-700 rounded-md flex justify-between items-center"
                                        >
                                            <div>
                                                <p className="text-sm font-medium">{device.name}</p>
                                                <p className="text-xs text-gray-500 dark:text-gray-400">마지막 접속: {device.lastAccess}</p>
                                            </div>
                                            <button
                                                className="text-sm text-red-500 hover:text-red-700"
                                                onClick={() => logoutDevice(device.id)}
                                            >
                                                로그아웃
                                            </button>
                                        </div>
                                    ))}

                                    {devices.length === 0 && (
                                        <p className="text-sm text-gray-500">연결된 기기가 없습니다.</p>
                                    )}
                                </div>
                            </div>
                        </div>
                    )}

                    {activeTab === 'appearance' && (
                        <div className="space-y-6">
                            <h2 className="text-lg font-medium">외관 설정</h2>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                                    테마
                                </label>
                                <div className="grid grid-cols-3 gap-4">
                                    <div
                                        className={`border rounded-md p-3 cursor-pointer flex items-center space-x-2 ${
                                            theme === 'light'
                                                ? 'border-blue-500 bg-blue-50 dark:bg-blue-900/20'
                                                : 'border-gray-300 dark:border-gray-700'
                                        }`}
                                        onClick={() => setTheme('light')}
                                    >
                                        <div className="w-4 h-4 rounded-full border border-gray-400 flex items-center justify-center">
                                            {theme === 'light' && (
                                                <div className="w-2 h-2 rounded-full bg-blue-500"></div>
                                            )}
                                        </div>
                                        <span className="text-sm">라이트</span>
                                    </div>
                                    <div
                                        className={`border rounded-md p-3 cursor-pointer flex items-center space-x-2 ${
                                            theme === 'dark'
                                                ? 'border-blue-500 bg-blue-50 dark:bg-blue-900/20'
                                                : 'border-gray-300 dark:border-gray-700'
                                        }`}
                                        onClick={() => setTheme('dark')}
                                    >
                                        <div className="w-4 h-4 rounded-full border border-gray-400 flex items-center justify-center">
                                            {theme === 'dark' && (
                                                <div className="w-2 h-2 rounded-full bg-blue-500"></div>
                                            )}
                                        </div>
                                        <span className="text-sm">다크</span>
                                    </div>
                                    <div
                                        className={`border rounded-md p-3 cursor-pointer flex items-center space-x-2 ${
                                            theme === 'system'
                                                ? 'border-blue-500 bg-blue-50 dark:bg-blue-900/20'
                                                : 'border-gray-300 dark:border-gray-700'
                                        }`}
                                        onClick={() => setTheme('system')}
                                    >
                                        <div className="w-4 h-4 rounded-full border border-gray-400 flex items-center justify-center">
                                            {theme === 'system' && (
                                                <div className="w-2 h-2 rounded-full bg-blue-500"></div>
                                            )}
                                        </div>
                                        <span className="text-sm">시스템</span>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                                    색상 모드
                                </label>
                                <div className="grid grid-cols-4 gap-4">
                                    <div
                                        className={`h-10 bg-blue-500 rounded-md cursor-pointer ${
                                            colorScheme === 'blue' ? 'ring-2 ring-offset-2 ring-blue-500' : ''
                                        }`}
                                        onClick={() => setColorScheme('blue')}
                                    ></div>
                                    <div
                                        className={`h-10 bg-purple-500 rounded-md cursor-pointer ${
                                            colorScheme === 'purple' ? 'ring-2 ring-offset-2 ring-purple-500' : ''
                                        }`}
                                        onClick={() => setColorScheme('purple')}
                                    ></div>
                                    <div
                                        className={`h-10 bg-green-500 rounded-md cursor-pointer ${
                                            colorScheme === 'green' ? 'ring-2 ring-offset-2 ring-green-500' : ''
                                        }`}
                                        onClick={() => setColorScheme('green')}
                                    ></div>
                                    <div
                                        className={`h-10 bg-red-500 rounded-md cursor-pointer ${
                                            colorScheme === 'red' ? 'ring-2 ring-offset-2 ring-red-500' : ''
                                        }`}
                                        onClick={() => setColorScheme('red')}
                                    ></div>
                                </div>
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    폰트 크기
                                </label>
                                <select
                                    className="w-full px-3 py-2 border border-gray-300 dark:border-gray-700 rounded-md"
                                    value={fontSize}
                                    onChange={(e) => setFontSize(e.target.value as FontSize)}
                                >
                                    <option value="sm">작게</option>
                                    <option value="md">중간</option>
                                    <option value="lg">크게</option>
                                    <option value="xl">아주 크게</option>
                                </select>
                            </div>

                            <div>
                                <label className="flex items-center space-x-2">
                                    <input
                                        type="checkbox"
                                        className="w-4 h-4 rounded border-gray-300"
                                        checked={animations}
                                        onChange={(e) => setAnimations(e.target.checked)}
                                    />
                                    <span className="text-sm text-gray-700 dark:text-gray-300">애니메이션 효과 사용</span>
                                </label>
                            </div>
                        </div>
                    )}
                </div>

                <div className="px-6 py-4 border-t border-gray-200 dark:border-gray-700 flex justify-end space-x-3">
                    <button
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 text-gray-700 dark:text-gray-300 rounded-md hover:bg-gray-50 dark:hover:bg-gray-700"
                        onClick={() => {
                            // 취소 버튼 - 설정 초기화 로직
                            setSuccessMessage('');
                            if (activeTab === 'security') {
                                setCurrentPassword('');
                                setNewPassword('');
                                setConfirmPassword('');
                                setPasswordError('');
                            }
                        }}
                    >
                        취소
                    </button>
                    <button
                        className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:opacity-50"
                        disabled={isSaving}
                        onClick={() => {
                            // 탭에 따라 적절한 저장 함수 호출
                            if (activeTab === 'general') {
                                saveGeneralSettings();
                            } else if (activeTab === 'notifications') {
                                saveNotificationSettings();
                            } else if (activeTab === 'security') {
                                if (newPassword) {
                                    changePassword();
                                }
                            } else if (activeTab === 'appearance') {
                                saveAppearanceSettings();
                            }
                        }}
                    >
                        {isSaving ? '저장 중...' : '저장하기'}
                    </button>
                </div>
            </div>
        </div>
    );
}