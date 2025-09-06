package com.service.menu.api.vo.request;

import com.service.menu.api.vo.data.MenuInfoVo;
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
public class RequestMenuInfoVo extends CmnRequestVo {

    List<MenuInfoVo> executeList;

    private String menuId;
    private String menuName;
    private Integer level;
    private String groupId;
    private String comCd;
    private String userId;
    private String parentMenuId;

}
