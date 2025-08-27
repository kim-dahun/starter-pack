package com.service.user.controller;

import com.service.user.service.UserAuthService;
import com.service.user.vo.request.RequestUserAuthVo;
import com.service.user.vo.response.ResponseUserAuthVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-service")
@CrossOrigin
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseUserAuthVo> api_login(@RequestBody RequestUserAuthVo requestUserAuthVo) {
        return userAuthService.login(requestUserAuthVo);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseUserAuthVo> api_signUp(@RequestBody RequestUserAuthVo requestUserAuthVo) {
        return userAuthService.registerUser(requestUserAuthVo);
    }

}
