import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
    // React의 Strict Mode 활성화 (개발 모드에서 컴포넌트를 2번 실행하여 잠재적 문제 확인)
    reactStrictMode: true,

    // 이미지 최적화 설정
    images: {
        domains: [], // 외부 이미지 도메인 허용 목록
        // 예: ['example.com', 'images.unsplash.com']
    },

    // 환경 변수 설정
    env: {
        API_URL : 'http://localhost', // api-gateway
        API_BASE_PORT : '8000',
        API_BASE_END_POINT : '/api'
        // 예: API_URL: 'https://api.example.com',
    },

    // 페이지 경로 재정의 (선택 사항)
    // async redirects() {
    //   return [
    //     {
    //       source: '/old-path',
    //       destination: '/new-path',
    //       permanent: true,
    //     },
    //   ]
    // },
};

export default nextConfig;
