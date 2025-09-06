package com.service.menu.api.vo.data;

import com.service.menu.domain.entity.MenuAuthDetail;
import com.service.menu.domain.entity.MenuGroupAuthDetail;
import com.service.menu.domain.entity.MenuInfoDetail;
import com.starter.lib.vo.common.CmnDataVo;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MenuDetailInfoVo extends CmnDataVo {

    private String menuDetailId;
    private String menuDetailName;
    private Integer sortSeq;
    private String remark;


    private String comCd;
    private String menuId;
    private String groupId;
    private String userId;
    private String useFlag;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;
    private String createUser;
    private String updateUser;

    private String deleteUser;

    public MenuInfoDetail toMenuInfoDetail() {
        return MenuInfoDetail.builder()
                .menuDetailId(menuDetailId)
                .menuDetailName(menuDetailName)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .remark(remark)
                .sortSeq(sortSeq)
                .build();
    }

    public MenuAuthDetail toMenuAuthDetail() {
        return MenuAuthDetail.builder()
                .menuDetailId(menuDetailId)
                .menuId(menuId)
                .useFlag(useFlag)
                .comCd(comCd)
                .userId(userId)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .build();
    }

    public MenuGroupAuthDetail toMenuGroupAuthDetail() {
        return MenuGroupAuthDetail.builder()
                .menuDetailId(menuDetailId)
                .menuId(menuId)
                .useFlag(useFlag)
                .comCd(comCd)
                .groupId(groupId)
                .createDate(createDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .createUser(createUser)
                .updateUser(updateUser)
                .deleteUser(deleteUser)
                .build();
    }


}
