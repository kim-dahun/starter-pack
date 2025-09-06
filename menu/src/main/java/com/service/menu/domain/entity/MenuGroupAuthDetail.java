package com.service.menu.domain.entity;

import com.service.menu.domain.entity.id.MenuGroupAuthDetailId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@IdClass(MenuGroupAuthDetailId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "MENU_GROUP_AUTH_DETAIL", schema = "MENU_MANAGE")
public class MenuGroupAuthDetail extends CmnBaseCUDEntity {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "MENU_ID", nullable = false, length = 50)
    private String menuId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "MENU_DETAIL_ID", nullable = false, length = 50)
    private String menuDetailId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "GROUP_ID", nullable = false, length = 50)
    private String groupId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "COM_CD", nullable = false, length = 50)
    private String comCd;

    @jakarta.validation.constraints.Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "USE_FLAG", length = 1)
    private String useFlag;

}