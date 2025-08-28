export default function AuthLayout({
                                       children,
                                   }: {
    children: React.ReactNode;
}) {
    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
            <div className="w-full max-w-md space-y-8">
                <div className="text-center">
                    <h1 className="text-3xl font-bold">My App</h1>
                    <p className="mt-2 text-gray-600">인증 플랫폼에 오신 것을 환영합니다</p>
                </div>
                {children}
            </div>
        </div>
    );
}