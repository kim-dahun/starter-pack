import api from "@/util/axios";
import {UserResponse} from "@/contexts/AuthContext";
import {CommonResponse} from "@/common/model/httpModel";


export const api_login = async (payload:unknown):Promise<UserResponse> => {
    const _res = await api.post<UserResponse>('/v1/user-service/login', payload);
    return _res.data;
}

export const api_register = async (payload:unknown):Promise<UserResponse> => {
    const _res = await api.post<UserResponse>('/v1/user-service/sign-up', payload);
    return _res.data;
}

export const api_logout = async (payload:unknown):Promise<UserResponse> => {
    const _res = await api.post<UserResponse>('/v1/user-service/logout', payload);
    return _res.data;
}