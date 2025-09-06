package com.service.menu.api.service;

import com.service.menu.api.vo.request.RequestMenuInfoVo;
import com.service.menu.api.vo.response.ResponseMenuInfoVo;
import org.springframework.http.ResponseEntity;

public interface MenuService {

    ResponseEntity<ResponseMenuInfoVo> getMenuInfoList(RequestMenuInfoVo requestMenuInfoVo);

    ResponseEntity<ResponseMenuInfoVo> doMenuInfoList(RequestMenuInfoVo requestMenuInfoVo);



}
