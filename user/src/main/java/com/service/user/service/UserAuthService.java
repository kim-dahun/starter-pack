package com.service.user.service;

import com.service.user.vo.request.RequestUserAuthVo;
import com.service.user.vo.response.ResponseUserAuthVo;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {

    ResponseEntity<ResponseUserAuthVo> login(RequestUserAuthVo requestUserAuthVo);

    ResponseEntity<ResponseUserAuthVo> registerUser(RequestUserAuthVo requestUserAuthVo);

}
