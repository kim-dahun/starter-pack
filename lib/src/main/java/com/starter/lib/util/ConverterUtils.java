package com.starter.lib.util;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;


public class ConverterUtils {

    private static final String REGEX = "[^0-9]";

    public static String getTimeKeySecond(LocalDateTime now){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    public static String getTimeKeyMillSecond(LocalDateTime now){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }

    public static String getTimeKeyDay(LocalDateTime now){
        return now.toString().replace(REGEX,"").substring(0,8);
    }

    public static String getTimeKeyMinute(LocalDateTime now){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return now.format(formatter);
    }

    public static String getDateTimeStringByFormat(LocalDateTime dateTime, String formatType){
        if(isValidPattern(formatType)){
            return dateTime.toString();
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(formatType);
        return dateFormat.format(dateTime);

    }

    public static String getDateTimeStringByFormat(Timestamp dateTime, String formatType){
        if(isValidPattern(formatType)){
            return dateTime.toString();
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(formatType);
        return dateFormat.format(dateTime.toLocalDateTime());
    }

    public static boolean isValidPattern(String pattern) {
        try {
            DateTimeFormatter.ofPattern(pattern);
            return false;
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return true;
        }
    }


    public static String getBase64EncdoingString(byte[] imgBytes){
        return Base64.getEncoder().encodeToString(imgBytes);
    }

    public static byte[] getByteArray(String serializedString){
        return serializedString.getBytes(StandardCharsets.UTF_8);
    }

}
