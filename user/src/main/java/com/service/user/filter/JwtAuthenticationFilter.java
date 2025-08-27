package com.service.user.filter;

import com.service.user.jwt.JwtTokenProvider;
import com.service.user.service.AuthServiceClient;
import com.service.user.vo.response.TokenResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 쿠키에서 액세스 토큰 추출
            String accessToken = extractTokenFromCookie(request, "access_token");
            String refreshToken = extractTokenFromCookie(request, "refresh_token");
            String requestURI = request.getRequestURI();

            // 공개 경로는 토큰 검사 생략
            if (isPublicPath(requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 액세스 토큰이 없거나 유효하지 않은 경우
            if (accessToken == null || !tokenProvider.validateToken(accessToken)) {
                log.debug("액세스 토큰이 없거나 유효하지 않습니다.");

                // 리프레시 토큰 확인
                if (refreshToken != null && tokenProvider.validateToken(refreshToken)) {
                    log.debug("리프레시 토큰으로 액세스 토큰 재발급 시도");

                    try {
                        // 리프레시 토큰으로 새 액세스 토큰 발급
                        String userId = tokenProvider.getUserIdFromToken(refreshToken);
                        String comCd = tokenProvider.getComCdFromToken(refreshToken);

                        TokenResponse tokenResponse = authServiceClient.refreshToken(refreshToken);

                        // 새 액세스 토큰 쿠키 설정
                        ResponseCookie newAccessTokenCookie = ResponseCookie.from("access_token", tokenResponse.getAccessToken())
                                .httpOnly(true)
                                .secure(true)
                                .sameSite("Lax")
                                .maxAge(tokenResponse.getExpiresIn())
                                .path("/")
                                .build();

                        response.addHeader("Set-Cookie", newAccessTokenCookie.toString());

                        // 새 액세스 토큰으로 인증 처리
                        Authentication authentication = tokenProvider.getAuthentication(tokenResponse.getAccessToken());
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        // 사용자 정보를 request에 추가
                        request.setAttribute("comCd", comCd);
                        request.setAttribute("userId", userId);

                        log.debug("리프레시 토큰으로 새 액세스 토큰 발급 성공");
                    } catch (Exception e) {
                        log.error("리프레시 토큰으로 액세스 토큰 재발급 실패: {}", e.getMessage());
                        sendUnauthorizedError(response, "Invalid refresh token");
                        return;
                    }
                } else {
                    // 리프레시 토큰도 없거나 유효하지 않은 경우
                    log.debug("리프레시 토큰도 없거나 유효하지 않습니다. 인증 실패.");
                    sendUnauthorizedError(response, "Authentication required");
                    return;
                }
            } else {
                // 액세스 토큰이 유효한 경우
                try {
                    Authentication authentication = tokenProvider.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 사용자 정보를 request에 추가
                    request.setAttribute("comCd", tokenProvider.getComCdFromToken(accessToken));
                    request.setAttribute("userId", tokenProvider.getUserIdFromToken(accessToken));

                    log.debug("유효한 JWT 토큰: {}", authentication.getName());
                } catch (Exception e) {
                    log.error("JWT 토큰 처리 중 오류: {}", e.getMessage());
                }
            }

        } catch (Exception e) {
            log.error("JWT 필터 처리 중 예외 발생: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> tokenCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookieName.equals(cookie.getName()))
                    .findFirst();

            if (tokenCookie.isPresent()) {
                return tokenCookie.get().getValue();
            }
        }
        return null;
    }

    private boolean isPublicPath(String requestURI) {
        return requestURI.contains("/users/signup") ||
                requestURI.contains("/users/login") ||
                requestURI.contains("/actuator") ||
                requestURI.contains("/error");
    }

    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }

}
