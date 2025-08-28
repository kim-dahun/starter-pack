'use client';

import { useState } from 'react';
import Topbar from './Topbar';
import Sidebar from './Sidebar';

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
    const [sidebarOpen, setSidebarOpen] = useState(true);

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    return (
        <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
            <Topbar toggleSidebar={toggleSidebar} />
            <Sidebar isOpen={sidebarOpen} />

            <main className={`pt-16 transition-all duration-300 ${sidebarOpen ? 'ml-64' : 'ml-0'}`}>
                <div className="p-6">
                    {children}
                </div>
            </main>
        </div>
    );
}