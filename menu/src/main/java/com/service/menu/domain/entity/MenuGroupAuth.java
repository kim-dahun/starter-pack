package com.service.menu.domain.entity;

import com.service.menu.domain.entity.id.MenuGroupAuthId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@IdClass(MenuGroupAuthId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "MENU_GROUP_AUTH", schema = "MENU_MANAGE")
public class MenuGroupAuth extends CmnBaseCUDEntity {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "MENU_ID", nullable = false, length = 50)
    private String menuId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "GROUP_ID", nullable = false, length = 50)
    private String groupId;

    @Id
    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "COM_CD", nullable = false, length = 100)
    private String comCd;

    @jakarta.validation.constraints.Size(max = 1)
    @Column(name = "PERMIT_READ", length = 1)
    private String permitRead;

    @jakarta.validation.constraints.Size(max = 1)
    @Column(name = "PERMIT_CREATE", length = 1)
    private String permitCreate;

    @jakarta.validation.constraints.Size(max = 1)
    @Column(name = "PERMIT_UPDATE", length = 1)
    private String permitUpdate;

    @jakarta.validation.constraints.Size(max = 1)
    @Column(name = "PERMIT_DELETE", length = 1)
    private String permitDelete;


}