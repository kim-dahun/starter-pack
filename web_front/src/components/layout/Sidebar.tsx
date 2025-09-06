'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { useState } from 'react';

// 메뉴 아이템 타입 정의
type MenuItem = {
    id: string;
    label: string;
    icon: React.ReactNode;
    href: string;
    submenu?: MenuItem[];
};

export default function Sidebar({ isOpen }: { isOpen: boolean }) {
    const pathname = usePathname();

    // 메뉴 아이템 상태 추적 (열림/닫힘)
    const [openMenus, setOpenMenus] = useState<Record<string, boolean>>({});

    // 메뉴 아이템 토글 함수
    const toggleMenu = (id: string) => {
        setOpenMenus(prev => ({
            ...prev,
            [id]: !prev[id]
        }));
    };

    // 메뉴 아이템 데이터
    const menuItems: MenuItem[] = [
        {
            id: 'dashboard',
            label: '대시보드',
            icon: (
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M2 10a8 8 0 018-8v8h8a8 8 0 11-16 0z" />
                    <path d="M12 2.252A8.014 8.014 0 0117.748 8H12V2.252z" />
                </svg>
            ),
            href: '/dashboard',
        },
        {
            id: 'settings_all',
            label: '설정',
            icon: (
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M11.49 3.17c-.38-1.56-2.6-1.56-2.98 0a1.532 1.532 0 01-2.286.948c-1.372-.836-2.942.734-2.106 2.106.54.886.061 2.042-.947 2.287-1.561.379-1.561 2.6 0 2.978a1.532 1.532 0 01.947 2.287c-.836 1.372.734 2.942 2.106 2.106a1.532 1.532 0 012.287.947c.379 1.561 2.6 1.561 2.978 0a1.533 1.533 0 012.287-.947c1.372.836 2.942-.734 2.106-2.106a1.533 1.533 0 01.947-2.287c1.561-.379 1.561-2.6 0-2.978a1.532 1.532 0 01-.947-2.287c.836-1.372-.734-2.942-2.106-2.106a1.532 1.532 0 01-2.287-.947zM10 13a3 3 0 100-6 3 3 0 000 6z" clipRule="evenodd" />
                </svg>
            ),
            href: '/dashboard/security',
            submenu: [
                {
                    id: 'profile',
                    label: '프로필',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path fillRule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clipRule="evenodd" />
                        </svg>
                    ),
                    href: '/dashboard/profile',
                },
                {
                    id: 'settings',
                    label: '환경설정',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path fillRule="evenodd" d="M2.166 4.999A11.954 11.954 0 0010 1.944 11.954 11.954 0 0017.834 5c.11.65.166 1.32.166 2.001 0 5.225-3.34 9.67-8 11.317C5.34 16.67 2 12.225 2 7c0-.682.057-1.35.166-2.001zm11.541 3.708a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd" />
                        </svg>
                    ),
                    href: '/dashboard/settings',
                },
            ],
        },
        {
            id: 'reports',
            label: '리포트',
            icon: (
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M6 2a2 2 0 00-2 2v12a2 2 0 002 2h8a2 2 0 002-2V7.414A2 2 0 0015.414 6L12 2.586A2 2 0 0010.586 2H6zm2 10a1 1 0 10-2 0v3a1 1 0 102 0v-3zm4-1a1 1 0 011 1v3a1 1 0 11-2 0v-3a1 1 0 011-1zm-2-2a1 1 0 10-2 0v5a1 1 0 102 0V9z" clipRule="evenodd" />
                </svg>
            ),
            href: '/dashboard/reports',
        },
    ];

    // 메뉴 아이템 렌더링 함수
    const renderMenuItem = (item: MenuItem) => {
        const isActive = pathname === item.href;
        const hasSubmenu = item.submenu && item.submenu.length > 0;
        const isSubmenuOpen = openMenus[item.id];

        return (
            <div key={item.id} className="mb-1">
                {hasSubmenu ? (
                    <>
                        <button
                            onClick={() => toggleMenu(item.id)}
                            className={`w-full flex items-center justify-between p-2 rounded-md ${
                                isActive ? 'bg-blue-500 text-white' : 'text-gray-700 dark:text-gray-200 hover:bg-gray-100 dark:hover:bg-gray-700'
                            }`}
                        >
                            <div className="flex items-center">
                                <span className="mr-3">{item.icon}</span>
                                <span>{item.label}</span>
                            </div>
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className={`h-4 w-4 transition-transform ${isSubmenuOpen ? 'rotate-180' : ''}`}
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                            >
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 9l-7 7-7-7" />
                            </svg>
                        </button>

                        {isSubmenuOpen && (
                            <div className="pl-6 mt-1 space-y-1">
                                {item.submenu?.map(subItem => (
                                    <Link
                                        key={subItem.id}
                                        href={subItem.href}
                                        className={`flex items-center p-2 rounded-md ${
                                            pathname === subItem.href
                                                ? 'bg-blue-500 text-white'
                                                : 'text-gray-700 dark:text-gray-200 hover:bg-gray-100 dark:hover:bg-gray-700'
                                        }`}
                                    >
                                        <span className="mr-3">{subItem.icon}</span>
                                        <span>{subItem.label}</span>
                                    </Link>
                                ))}
                            </div>
                        )}
                    </>
                ) : (
                    <Link
                        href={item.href}
                        className={`flex items-center p-2 rounded-md ${
                            isActive
                                ? 'bg-blue-500 text-white'
                                : 'text-gray-700 dark:text-gray-200 hover:bg-gray-100 dark:hover:bg-gray-700'
                        }`}
                    >
                        <span className="mr-3">{item.icon}</span>
                        <span>{item.label}</span>
                    </Link>
                )}
            </div>
        );
    };

    return (
        <aside
            className={`bg-white dark:bg-gray-800 fixed left-0 top-16 bottom-0 w-64 shadow-md transition-transform duration-300 ease-in-out z-10 ${
                isOpen ? 'translate-x-0' : '-translate-x-full'
            }`}
        >
            <div className="p-4 h-full overflow-y-auto">
                <nav className="space-y-1">
                    {menuItems.map(renderMenuItem)}
                </nav>
            </div>
        </aside>
    );
}