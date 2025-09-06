package com.starter.lib.service;

public interface MessageService {

    String getMessage(String langCode, Object[] msgParams , String msgCode);

    String getMessage( Object[] msgParams, String msgCode);
}
