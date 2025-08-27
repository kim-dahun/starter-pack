package com.service.user.service.impl;

import com.service.user.entity.UserInfo;
import com.service.user.entity.id.UserInfoId;
import com.service.user.repository.UserInfoRepository;
import com.service.user.service.AuthServiceClient;
import com.service.user.service.UserAuthService;
import com.service.user.vo.data.UserInfoVo;
import com.service.user.vo.request.RequestUserAuthVo;
import com.service.user.vo.response.ResponseUserAuthVo;
import com.service.user.vo.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final AuthServiceClient authServiceClient;
    private final UserInfoRepository userInfoRepository;

    @Override
    public ResponseEntity<ResponseUserAuthVo> login(RequestUserAuthVo requestUserAuthVo) {

        ResponseUserAuthVo responseUserAuthVo = new ResponseUserAuthVo();
        try {
            responseUserAuthVo.setResponseCode(200);
            responseUserAuthVo.setResponseMessage("SUCCESS");
            String userId= requestUserAuthVo.getUserId();
            String comCd = requestUserAuthVo.getComCd();
            String userPassword = requestUserAuthVo.getUserPassword();
            isValidParameter(userId, userPassword, comCd);

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
            responseUserAuthVo.setUserInfoVo(userInfoVo);
            return ResponseEntity.status(200).headers(headers).body(responseUserAuthVo);

        } catch (Exception e){
            return ResponseEntity.status(500).body(responseUserAuthVo);
        }
    }

    private void isValidParameter(String userId, String userPassword, String comCd) {
        if(userId == null || userPassword == null || comCd == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }
    }

    @Override
    public ResponseEntity<ResponseUserAuthVo> registerUser(RequestUserAuthVo requestUserAuthVo) {
        return null;
    }
}
