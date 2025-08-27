package com.service.user.controller;

import com.service.user.vo.common.CmnResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.service.user.constants.MessageConstants.MSG_TOKEN_VALIDATION_SUCCESS_ALERT;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {


    // 구체적인 API를 제외한 모든 API 호출을 받아서 필터 통과 시 200, 통과 못할 시 401 리턴 API_GATEWAY로.
    @GetMapping
    public ResponseEntity<CmnResponseVo> api_tokenValid() {
        CmnResponseVo responseVo = new CmnResponseVo();
        responseVo.setResponseMessage(MSG_TOKEN_VALIDATION_SUCCESS_ALERT);
        return ResponseEntity.ok(responseVo);
    }

}
