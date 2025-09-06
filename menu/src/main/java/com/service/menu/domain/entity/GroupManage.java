package com.service.menu.domain.entity;

import com.service.menu.domain.entity.id.GroupManageId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@IdClass(GroupManageId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "GROUP_MANAGE", schema = "MENU_MANAGE")
public class GroupManage extends CmnBaseCUDEntity {
    @Id
    @Size(max = 50)
    @NotNull
    @Column(name = "COM_CD", nullable = false, length = 50)
    private String comCd;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "GROUP_ID", nullable = false, length = 50)
    private String groupId;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "GROUP_NAME", length = 100)
    private String groupName;

}