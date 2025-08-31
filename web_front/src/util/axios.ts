import axios from 'axios';
import {CommonResponse} from "@/common/model/httpModel";

// axios 기본 인스턴스 설정
const apiClient = axios.create({
    baseURL: 'http://localhost:8000/api',
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true,
    timeout: 180000,
});

// 간소화된 API 유틸리티
const api = {
    /**
     * GET 요청
     * @param url 요청 경로
     * @param params 쿼리 파라미터 객체
     */
    get: async<T> (url: string, params?: unknown): Promise<T | CommonResponse> => {
        try {
            const response = await apiClient.get(url, { params });
            return response.data;
        } catch (e) {
            console.error(e);
            return {
                responseCode: 500,
                responseMessage: 'FAIL'
            };
        }

    },

    /**
     * POST 요청
     * @param url 요청 경로
     * @param body 요청 바디
     */
    post: async<T> (url: string, body?: unknown): Promise<T | CommonResponse> => {
        try {
            const response = await apiClient.post(url, body);
            return response.data;
        } catch (e) {
            console.error(e);
            return {
                responseCode: 500,
                responseMessage: 'FAIL'
            };
        }
    },

    /**
     * PUT 요청
     * @param url 요청 경로
     * @param body 요청 바디
     */
    put: async<T> (url: string, body?: unknown): Promise<T | CommonResponse> => {
        try {
            const response = await apiClient.put(url, body);
            return response.data;
        } catch (e) {
            console.error(e);
            return {
                responseCode: 500,
                responseMessage: 'FAIL'
            };
        }
    },

    /**
     * PATCH 요청
     * @param url 요청 경로
     * @param body 요청 바디
     */
    patch: async<T> (url: string, body?: unknown): Promise<T | CommonResponse> => {
        try {
            const response = await apiClient.patch(url, body);
            return response.data;
        } catch (e) {
            console.error(e);
            return {
                responseCode: 500,
                responseMessage: 'FAIL'
            };
        }
    },

    /**
     * DELETE 요청
     * @param url 요청 경로
     * @param params 쿼리 파라미터 객체
     */
    delete: async<T> (url: string, params?: any): Promise<CommonResponse> => {
        try {
            const response = await apiClient.delete(url, {params});
            return response.data;
        } catch (e) {
            console.error(e);
            return {
                responseCode: 500,
                responseMessage: 'FAIL'
            };
        }
    }
};

export default api;