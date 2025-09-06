package com.service.user.api.service.impl;

import com.service.user.domain.entity.UserAuth;
import com.service.user.domain.entity.UserInfo;
import com.service.user.domain.entity.id.UserInfoId;
import com.service.user.domain.repository.UserAuthRepository;
import com.service.user.domain.repository.UserInfoRepository;
import com.service.user.api.service.AuthServiceClient;
import com.service.user.api.service.UserAuthService;
import com.service.user.api.vo.data.UserInfoVo;
import com.service.user.api.vo.request.RequestUserAuthVo;
import com.service.user.api.vo.response.ResponseUserAuthVo;
import com.service.user.api.vo.response.TokenResponse;
import com.starter.lib.vo.common.CmnResponseVo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Arrays;
import java.util.List;

import static com.starter.lib.constants.MessageConstants.MSG_RESPONSE_COMMON_FAIL;
import static com.starter.lib.constants.MessageConstants.MSG_RESPONSE_SUCCESS;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final AuthServiceClient authServiceClient;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthRepository userAuthRepository;

    @Override
    public ResponseEntity<ResponseUserAuthVo> login(RequestUserAuthVo requestUserAuthVo) {

        ResponseUserAuthVo responseUserAuthVo = new ResponseUserAuthVo();
        try {
            responseUserAuthVo.setResponseCode(HttpStatus.OK);
            responseUserAuthVo.setResponseMessage(MSG_RESPONSE_SUCCESS);
            String userId= requestUserAuthVo.getUserId();
            String comCd = requestUserAuthVo.getComCd();
            String userPassword = requestUserAuthVo.getUserPassword();
            isValidParameter(userId, userPassword, comCd);
            if(!checkUserPassword(userId, userPassword, comCd)){
                throw new UserPrincipalNotFoundException("Username or password is incorrect");
            }

            TokenResponse tokenResponse = authServiceClient.getUserToken(userId, comCd, List.of("USER"));
            HttpHeaders headers = new HttpHeaders();

            // 액세스 토큰 쿠키
            ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", tokenResponse.getAccessToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")  // 크로스 사이트 GET 요청 허용
                    .maxAge(tokenResponse.getExpiresIn())
                    .path("/")
                    .build();

            // 리프레시 토큰 쿠키
            ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", tokenResponse.getRefreshToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .maxAge(86400 * 30)
                    .path("/api/v1/auth")
                    .build();
            headers.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
            headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

            UserInfo userInfo = userInfoRepository.findById(UserInfoId.builder().userId(userId).comCd(comCd).build()).orElseThrow();
            UserInfoVo userInfoVo = new UserInfoVo(userInfo);
            responseUserAuthVo.setData(userInfoVo);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(responseUserAuthVo);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUserAuthVo);
        }
    }

    private Boolean checkUserPassword(String userId, String userPassword, String comCd) {
        UserAuth loginUser = userAuthRepository.findById(UserInfoId.builder().userId(userId).comCd(comCd).build()).orElseThrow();
        return passwordEncoder.matches(userPassword, loginUser.getPassword());


    }

    private void isValidParameter(String userId, String userPassword, String comCd) {
        if(userId == null || userPassword == null || comCd == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseUserAuthVo> registerUser(RequestUserAuthVo requestUserAuthVo) {

        ResponseUserAuthVo responseUserAuthVo = new ResponseUserAuthVo();
        UserInfoVo userInfoVo = requestUserAuthVo.getUserInfoVo();
        try {
            UserInfo userInfo = userInfoVo.toEntity();
            userInfoRepository.save(userInfo);
            UserAuth userAuth = getUserAuth(userInfoVo);
            userAuthRepository.save(userAuth);

            responseUserAuthVo.setResponseCode(HttpStatus.OK);
            responseUserAuthVo.setResponseMessage(MSG_RESPONSE_SUCCESS);
            responseUserAuthVo.setData(userInfoVo);
            return ResponseEntity.ok(responseUserAuthVo);
        } catch (Exception e) {
            responseUserAuthVo.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseUserAuthVo.setResponseMessage(MSG_RESPONSE_COMMON_FAIL);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUserAuthVo);
        }
    }

    private UserAuth getUserAuth(UserInfoVo userInfoVo) {
        return UserAuth.builder()
                .userPassword(passwordEncoder.encode(userInfoVo.getUserPassword()))
                .userId(userInfoVo.getUserId())
                .comCd(userInfoVo.getComCd())
                .status("Y")
                .roles(List.of("USER"))
                .build();
    }


    @Override
    public ResponseEntity<CmnResponseVo> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 액세스 토큰과 리프레시 토큰 쿠키 추출
            String accessToken = extractTokenFromCookie(request, "access_token");
            String refreshToken = extractTokenFromCookie(request, "refresh_token");

            // 토큰이 존재하면 블랙리스트에 추가
            if (accessToken != null) {
                authServiceClient.invalidateToken(accessToken);
            }
            if (refreshToken != null) {
                authServiceClient.invalidateToken(refreshToken);
            }

            // 응답에서 쿠키 삭제 (ResponseCookie 사용)
            ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", "")
                    .httpOnly(true)
                    .secure(true)
                    .maxAge(0)
                    .path("/")
                    .build();

            ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", "")
                    .httpOnly(true)
                    .secure(true)
                    .maxAge(0)
                    .path("/api/v1/auth")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
            response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

            return ResponseEntity.ok(new CmnResponseVo(HttpStatus.OK, MSG_RESPONSE_SUCCESS));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CmnResponseVo(HttpStatus.INTERNAL_SERVER_ERROR, MSG_RESPONSE_COMMON_FAIL));
        }
    }

    private void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    // 쿠키에서 토큰 추출하는 헬퍼 메서드
    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookieName.equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return null;
    }

}
