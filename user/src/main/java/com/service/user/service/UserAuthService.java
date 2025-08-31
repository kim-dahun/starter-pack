package com.service.user.service;

import com.service.user.vo.common.CmnResponseVo;
import com.service.user.vo.request.RequestUserAuthVo;
import com.service.user.vo.response.ResponseUserAuthVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {

    ResponseEntity<ResponseUserAuthVo> login(RequestUserAuthVo requestUserAuthVo);

    ResponseEntity<CmnResponseVo> logout(HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<ResponseUserAuthVo> registerUser(RequestUserAuthVo requestUserAuthVo);

}
