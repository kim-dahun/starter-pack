package com.service.user.service;

import com.service.user.vo.response.TokenResponse;

import java.util.List;

public interface AuthServiceClient {
    /**
     * 사용자 로그인을 위한 토큰 요청
     */
    TokenResponse getUserToken(String userId, String comCd, List<String> roles);

    /**
     * 토큰 검증 요청
     */
    boolean validateToken(String token);

    /**
     * 리프레시 토큰으로 새 액세스 토큰 발급
     */
    TokenResponse refreshToken(String refreshToken);

    void invalidateToken(String refreshToken);
}
