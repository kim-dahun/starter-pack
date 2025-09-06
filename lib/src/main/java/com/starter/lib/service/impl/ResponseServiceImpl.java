package com.starter.lib.service.impl;



import com.starter.lib.constants.MessageConstants;
import com.starter.lib.service.MessageService;
import com.starter.lib.service.ResponseService;
import com.starter.lib.vo.common.CmnResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.starter.lib.constants.MessageConstants.*;


@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final MessageService messageService;

    @Override
    public CmnResponseVo getLoginFail(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setResponseCode(HttpStatus.UNAUTHORIZED);
        cmnResponseVo.setResponseMessage(messageService.getMessage(langCode,null, FAIL_LOGIN));
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getCreateUserSuccess() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null,CREATE_ACCOUNT_SUCCESS))
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                .resultData(userInfo)
                .build();
    }

    @Override
    public CmnResponseVo getCreateUserFail() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null,CREATE_ACCOUNT_FAIL))
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @Override
    public CmnResponseVo getNotAccessUserId(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setResponseCode(HttpStatus.FORBIDDEN);
        cmnResponseVo.setResponseMessage(messageService.getMessage(langCode,null,FAIL_ACCESS_ACCOUNT));
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getLoginSuccess(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setResponseCode(HttpStatus.OK);
        cmnResponseVo.setResponseMessage(messageService.getMessage(langCode,null,LOGIN_SUCCESS));
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getNotExistUserId() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null,NOT_EXIST_ACCOUNT))
                .responseCode(HttpStatus.NO_CONTENT)
                .build();
    }


    @Override
    public CmnResponseVo getSearchSuccess(){
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null, SEARCH_SUCCESS))
                .responseCode(HttpStatus.OK)
                .build();
    }

    @Override
    public CmnResponseVo getSearchFail(){
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null, SEARCH_FAIL))
                .responseCode(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public CmnResponseVo getModifySuccess() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null, MODIFY_SUCCESS))
                .responseCode(HttpStatus.OK)
                .build();
    }

    @Override
    public CmnResponseVo getModifyFailed() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null, MODIFY_FAIL))
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @Override
    public CmnResponseVo getModifyPartiallySucceed() {
        return CmnResponseVo.builder()
                .responseMessage(messageService.getMessage(KO,null, MODIFY_PARTIALLY_SUCCESS))
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

}
