package com.service.menu.api.vo.data;

import com.service.menu.domain.entity.MenuAuth;
import com.service.menu.domain.entity.MenuGroupAuth;
import com.service.menu.domain.entity.MenuInfo;
import com.service.menu.domain.entity.id.GroupManageId;
import com.starter.lib.vo.common.CmnDataVo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MenuInfoVo extends CmnDataVo {

    private String menuId;
    private String menuName;
    private Integer menuLevel;
    private String parentMenuId;
    private String langCode;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;
    private String createUser;
    private String updateUser;

    private String deleteUser;
    private Integer sortSeq;


    private String groupId;
    private String comCd;
    private String userId;

    private String permitRead;
    private String permitCreate;
    private String permitUpdate;
    private String permitDelete;

    List<MenuDetailInfoVo> subList;

    public MenuInfo toMenuInfoEntity() {
        return MenuInfo.builder()
                .menuLevel(menuLevel)
                .menuName(menuName)
                .parentMenuId(parentMenuId)
                .langCode(langCode)
                .menuId(menuId)
                .sortSeq(sortSeq)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .build();
    }

    public MenuAuth toMenuAuthEntity() {
        return MenuAuth.builder()
                .menuId(menuId)
                .comCd(comCd)
                .userId(userId)
                .permitRead(permitRead)
                .permitCreate(permitCreate)
                .permitUpdate(permitUpdate)
                .permitDelete(permitDelete)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .build();
    }

    public MenuGroupAuth toMenuGroupAuthEntity() {
        return MenuGroupAuth.builder()
                .menuId(menuId)
                .groupId(groupId)
                .permitRead(permitRead)
                .permitCreate(permitCreate)
                .permitUpdate(permitUpdate)
                .permitDelete(permitDelete)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .build();
    }


}
