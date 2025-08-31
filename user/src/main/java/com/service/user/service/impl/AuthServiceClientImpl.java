package com.service.user.service.impl;

import com.service.user.entity.UserAuth;
import com.service.user.jwt.JwtTokenProvider;
import com.service.user.repository.UserAuthRepository;
import com.service.user.service.AuthServiceClient;
import com.service.user.vo.request.TokenRequest;
import com.service.user.vo.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceClientImpl implements AuthServiceClient {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthRepository userAuthRepository;

    /**
     * 사용자 로그인을 위한 토큰 요청
     */
    @Override
    public TokenResponse getUserToken(String userId, String comCd, List<String> roles) {
        // UserAuth 정보 조회
        UserAuth userAuth = userAuthRepository.findByComCdAndUserId(comCd, userId)
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

        // 역할 설정 (외부에서 전달받은 역할로 덮어쓰기)
        userAuth.setRoles(roles);

        // 인증 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userAuth,
                null,
                roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

        // 토큰 생성
        String accessToken = jwtTokenProvider.createToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

        // 토큰 응답 생성
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(3600) // 토큰 유효시간 (초)
                .build();
    }

    /**
     * 토큰 검증 요청
     */
    @Override
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    /**
     * 리프레시 토큰으로 새 액세스 토큰 발급
     */
    @Override
    public TokenResponse refreshToken(String refreshToken) {
        // 리프레시 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        try {
            // 리프레시 토큰에서 사용자 정보 추출
            String userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
            String comCd = jwtTokenProvider.getComCdFromToken(refreshToken);

            // 사용자 정보 조회
            UserAuth userAuth = userAuthRepository.findByComCdAndUserId(comCd, userId)
                    .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId + " and comCd: " + comCd));

            // 인증 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userAuth,
                    null,
                    userAuth.getAuthorities()
            );

            // 새 액세스 토큰 생성 (리프레시 토큰은 재사용)
            String newAccessToken = jwtTokenProvider.createToken(authentication);

            return TokenResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken) // 기존 리프레시 토큰 유지
                    .tokenType("Bearer")
                    .expiresIn(3600) // 토큰 유효시간 (초)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error refreshing token: " + e.getMessage(), e);
        }
    }

    @Override
    public void invalidateToken(String token) {
        jwtTokenProvider.invalidateToken(token);
    }

}
