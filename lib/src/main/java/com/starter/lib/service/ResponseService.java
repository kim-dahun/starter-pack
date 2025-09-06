package com.starter.lib.service;


import com.starter.lib.vo.common.CmnResponseVo;

public interface ResponseService {

    CmnResponseVo getNotExistUserId();

    CmnResponseVo getLoginFail(String langCode);

    CmnResponseVo getCreateUserSuccess();

    CmnResponseVo getCreateUserFail();

    CmnResponseVo getNotAccessUserId(String langCode);

    CmnResponseVo getLoginSuccess(String langCode);

    CmnResponseVo getSearchSuccess();

    CmnResponseVo getSearchFail();

    CmnResponseVo getModifySuccess();

    CmnResponseVo getModifyFailed();

    CmnResponseVo getModifyPartiallySucceed();

}
