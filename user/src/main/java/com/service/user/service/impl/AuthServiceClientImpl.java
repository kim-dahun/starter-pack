package com.service.user.service.impl;

import com.service.user.service.AuthServiceClient;
import com.service.user.vo.request.TokenRequest;
import com.service.user.vo.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceClientImpl implements AuthServiceClient {


    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Value("${spring.security.oauth2.client.provider.auth-service.token-uri}")
    private String tokenUri;

    /**
     * OAuth2 클라이언트 자격 증명 방식으로 액세스 토큰 획득
     */
    private OAuth2AccessToken getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("auth-service")
                .principal("user-service")
                .build();

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        return Objects.requireNonNull(authorizedClient).getAccessToken();
    }

    /**
     * 사용자 로그인을 위한 토큰 요청
     */
    @Override
    public TokenResponse getUserToken(String userId, String comCd, List<String> roles) {
        // 클라이언트 자격 증명으로 액세스 토큰 획득
        OAuth2AccessToken accessToken = getAccessToken();

        // 액세스 토큰으로 사용자 토큰 요청
        com.service.user.vo.request.TokenRequest request = new TokenRequest(userId, comCd, roles);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());

        HttpEntity<TokenRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(
                tokenUri + "/user",  // 사용자 토큰 발급 엔드포인트
                entity,
                TokenResponse.class
        );
    }

    /**
     * 토큰 검증 요청
     */
    @Override
    public boolean validateToken(String token) {
        // 클라이언트 자격 증명으로 액세스 토큰 획득
        OAuth2AccessToken accessToken = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());

        HttpEntity<String> entity = new HttpEntity<>(token, headers);

        return Boolean.TRUE.equals(restTemplate.postForObject(
                tokenUri + "/validate",  // 토큰 검증 엔드포인트
                entity,
                Boolean.class
        ));
    }

}
