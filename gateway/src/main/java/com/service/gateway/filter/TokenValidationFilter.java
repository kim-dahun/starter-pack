package com.service.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class TokenValidationFilter extends AbstractGatewayFilterFactory<TokenValidationFilter.Config> {

    private final WebClient.Builder webClientBuilder;
    private final String tokenValidationUrl;

    public TokenValidationFilter(WebClient.Builder webClientBuilder,
                                 @org.springframework.beans.factory.annotation.Value("${token.validation.url}") String tokenValidationUrl) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
        this.tokenValidationUrl = tokenValidationUrl;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // 제외 경로 확인
            if (isExcludedPath(path, config.getExcludedPaths())) {
                return chain.filter(exchange);
            }

            // 쿠키에서 액세스 토큰 추출
            String accessToken = extractTokenFromCookies(exchange);

            if (accessToken == null) {
                log.debug("액세스 토큰이 없습니다. 인증 실패.");
                return onError(exchange, "No access token", HttpStatus.UNAUTHORIZED);
            }

            // 토큰 검증 요청
            return webClientBuilder.build()
                    .get()
                    .uri(tokenValidationUrl)
                    .cookie("access_token", accessToken)
                    .exchangeToMono(clientResponse -> {
                        if (clientResponse.statusCode().is2xxSuccessful()) {
                            log.debug("토큰 검증 성공");
                            return chain.filter(exchange);
                        } else {
                            log.debug("토큰 검증 실패: {}", clientResponse.statusCode());
                            return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
                        }
                    })
                    .onErrorResume(e -> {
                        log.error("토큰 검증 중 오류 발생: {}", e.getMessage());
                        return onError(exchange, "Token validation error", HttpStatus.INTERNAL_SERVER_ERROR);
                    });
        };
    }

    private String extractTokenFromCookies(ServerWebExchange exchange) {
        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        if (cookies.containsKey("access_token")) {
            return cookies.getFirst("access_token").getValue();
        }
        return null;
    }

    private boolean isExcludedPath(String path, List<String> excludedPaths) {
        return excludedPaths.stream().anyMatch(path::contains) ||
                path.contains("/actuator") ||
                path.contains("/error");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    @Data
    public static class Config {
        private List<String> excludedPaths = Arrays.asList("/login", "/signup");
    }

}
