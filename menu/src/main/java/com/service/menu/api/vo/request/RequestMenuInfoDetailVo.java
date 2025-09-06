package com.service.menu.api.vo.request;

import com.service.menu.api.vo.data.MenuDetailInfoVo;
import com.starter.lib.vo.common.CmnRequestVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RequestMenuInfoDetailVo extends CmnRequestVo {

    List<MenuDetailInfoVo> executeList;

    private String menuDetailId;
    private String menuDetailName;
    private String groupId;
    private String comCd;
    private String userId;

}
