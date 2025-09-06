package com.service.menu.api.vo.data;

import com.service.menu.domain.entity.GroupManage;
import com.starter.lib.vo.common.CmnDataVo;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GroupInfoVo extends CmnDataVo {

    private String comCd;
    private String groupId;
    private String groupName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private String createUser;
    private String updateUser;
    private String deleteUser;

    public GroupManage toEntity() {
        return GroupManage.builder()
                .comCd(this.comCd)
                .groupId(this.groupId)
                .groupName(this.groupName)
                .createDate(this.createDate)
                .updateDate(this.updateDate)
                .deleteDate(this.deleteDate)
                .createUser(this.createUser)
                .updateUser(this.updateUser)
                .deleteUser(this.deleteUser)
                .build();
    }

}
