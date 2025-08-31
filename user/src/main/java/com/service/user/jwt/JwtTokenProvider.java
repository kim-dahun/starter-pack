package com.service.user.jwt;

import com.service.user.entity.UserAuth;
import com.service.user.entity.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final long tokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final Set<String> tokenBlacklist = ConcurrentHashMap.newKeySet();

    /**
     * 토큰을 무효화하여 블랙리스트에 추가합니다.
     * @param token 무효화할 JWT 토큰
     */
    public void invalidateToken(String token) {
        tokenBlacklist.add(token);
    }

    /**
     * 토큰이 블랙리스트에 있는지 확인합니다.
     * @param token 확인할 JWT 토큰
     * @return 블랙리스트에 있으면 true, 그렇지 않으면 false
     */
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
                            @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
    }

    // 액세스 토큰 생성 - UserAuth를 직접 받는 방식
    public String createToken(UserAuth userAuth) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        String authorities = userAuth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(userAuth.getUsername())
                .claim("comCd", userAuth.getComCd())
                .claim("userId", userAuth.getUserId())
                .claim("auth", authorities)
                .claim("status", userAuth.getStatus())
                .setIssuedAt(new Date(now))
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512);

        // UserInfo 정보가 있다면 추가
        if (userAuth.getUserInfo() != null) {
            UserInfo userInfo = userAuth.getUserInfo();
            jwtBuilder
                    .claim("userName", userInfo.getUserName())
                    .claim("email", userInfo.getEmail())
                    .claim("userType", userInfo.getUserType());
        }

        return jwtBuilder.compact();
    }

    // 액세스 토큰 생성 - Authentication 객체를 받는 방식
    public String createToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserAuth userAuth) {
            return createToken(userAuth);
        } else {
            // 기존 로직 유지 (UserAuth가 아닌 경우를 위한 예외 처리)
            String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            long now = (new Date()).getTime();
            Date validity = new Date(now + this.tokenValidityInMilliseconds);

            return Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim("auth", authorities)
                    .setIssuedAt(new Date(now))
                    .setExpiration(validity)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
        }
    }

    // 리프레시 토큰 생성 - UserAuth를 직접 받는 방식
    public String createRefreshToken(UserAuth userAuth) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(userAuth.getUsername())
                .claim("comCd", userAuth.getComCd())
                .claim("userId", userAuth.getUserId())
                .setIssuedAt(new Date(now))
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // 리프레시 토큰 생성 - Authentication 객체를 받는 방식
    public String createRefreshToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserAuth userAuth) {
            return createRefreshToken(userAuth);
        } else {
            long now = (new Date()).getTime();
            Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);

            return Jwts.builder()
                    .setSubject(authentication.getName())
                    .setIssuedAt(new Date(now))
                    .setExpiration(validity)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
        }
    }

    // 토큰에서 인증 정보 추출
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth", String.class).split(","))
                        .filter(auth -> !auth.trim().isEmpty())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserAuth 객체 생성
        UserAuth userAuth = new UserAuth();
        userAuth.setComCd(claims.get("comCd", String.class));
        userAuth.setUserId(claims.get("userId", String.class));
        userAuth.setStatus(claims.get("status", String.class));

        // UserInfo가 있다면 설정
        if (claims.containsKey("userName") || claims.containsKey("email") || claims.containsKey("userType")) {
            UserInfo userInfo = new UserInfo();
            userInfo.setComCd(claims.get("comCd", String.class));
            userInfo.setUserId(claims.get("userId", String.class));
            userInfo.setUserName(claims.get("userName", String.class));
            userInfo.setEmail(claims.get("email", String.class));
            userInfo.setUserType(claims.get("userType", String.class));
            userAuth.setUserInfo(userInfo);
        }

        return new UsernamePasswordAuthenticationToken(userAuth, token, authorities);
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        if (isTokenBlacklisted(token)) {
            return false;
        }
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }

    // 토큰에서 userId 추출
    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId", String.class);
    }

    // 토큰에서 comCd 추출
    public String getComCdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("comCd", String.class);
    }
}
