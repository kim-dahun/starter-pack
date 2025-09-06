package com.starter.lib.service.impl;


import com.starter.lib.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String langCode, Object[] msgParams ,String msgCode) {
        Locale locale = Locale.of(langCode);

        return messageSource.getMessage(msgCode,msgParams, locale);


    }

    @Override
    public String getMessage(Object[] msgParams, String msgCode) {
        Locale locale = Locale.KOREAN;
        return messageSource.getMessage(msgCode,msgParams, locale);
    }
}
