package com.starter.lib.model;

import java.util.List;
import java.util.Map;

public interface NativeQueryUtilsModel {

    String readXmlQueryFile(String packageName, String queryId);

    String getQueryString(String packageName, String queryId);

    String getQueryString(String packageName, String queryId, Map<String, Object> eqParamMap);

    String getQueryString(String packageName, String queryId, Map<String, Object> eqParamMap, Map<String, Object> inParamMap);

    Map<String, Object> getInParamMapToStringMap(Map<String, Object> inParamMap);

    String replaceParameter(String queryString, String parameter, String value);

    List<String> getKeyListOrderByLength(Map<String, Object> paramMap);

    List getSelectQueryResults(String packageName, String queryId);

    Object getSelectQueryResult(String packageName, String queryId);

    <T> T getSelectQueryResult(String packageName, String queryId, Class<T> clazz);

    <T> List<T> getSelectQueryResults(String packageName, String queryId, Class<T> clazz);

    Object getSelectQueryResult(String packageName, String queryId, Map<String, Object> eqParamMap);

    List getSelectQueryResults(String packageName, String queryId, Map<String, Object> eqParamMap);

    <T> T getSelectQueryResult(String packageName, String queryId, Map<String, Object> eqParamMap, Class<T> clazz);

    <T> List<T> getSelectQueryResults(String packageName, String queryId, Map<String, Object> eqParamMap, Class<T> clazz);


    List getSelectQueryResults(String packageName, String queryId, Map<String, Object> eqParamMap, Map<String, Object> inParamMap);

    Object getSelectQueryResult(String packageName, String queryId, Map<String, Object> eqParamMap, Map<String, Object> inParamMap);

    <T> T getSelectQueryResult(String packageName, String queryId, Map<String, Object> eqParamMap, Map<String, Object> inParamMap, Class<T> clazz);

    <T> List<T> getSelectQueryResults(String packageName, String queryId, Map<String, Object> eqParamMap, Map<String, Object> inParamMap, Class<T> clazz);

    <T> List<T> getQueryResultListByQueryString(String queryString, Class<T> clazz);

    List<Map<String, Object>> getQueryResultListByQueryString(String queryString);

    Object getQueryResultByQueryString(String queryString, Class<?> clazz);

    int executeQueryString(String queryString, List<Map<String,?>> paramMaps);

}
