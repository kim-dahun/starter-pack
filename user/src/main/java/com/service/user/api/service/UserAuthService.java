package com.service.user.api.service;

import com.service.user.api.vo.request.RequestUserAuthVo;
import com.service.user.api.vo.response.ResponseUserAuthVo;
import com.starter.lib.vo.common.CmnResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {

    ResponseEntity<ResponseUserAuthVo> login(RequestUserAuthVo requestUserAuthVo);

    ResponseEntity<CmnResponseVo> logout(HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<ResponseUserAuthVo> registerUser(RequestUserAuthVo requestUserAuthVo);

}
