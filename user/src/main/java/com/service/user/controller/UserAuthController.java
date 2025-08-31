package com.service.user.controller;

import com.service.user.service.UserAuthService;
import com.service.user.vo.request.RequestUserAuthVo;
import com.service.user.vo.response.ResponseUserAuthVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.service.user.constants.UrlConstants.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL+"/v1/user-service")
@CrossOrigin
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseUserAuthVo> api_login(@RequestBody RequestUserAuthVo requestUserAuthVo) {
        return userAuthService.login(requestUserAuthVo);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseUserAuthVo> api_signUp(@RequestBody RequestUserAuthVo requestUserAuthVo) {
        return userAuthService.registerUser(requestUserAuthVo);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return userAuthService.logout(request,response);
    }

}
