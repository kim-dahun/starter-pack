package com.service.menu.domain.entity;

import com.service.menu.domain.entity.id.MenuAuthDetailId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@IdClass(MenuAuthDetailId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "MENU_AUTH_DETAIL", schema = "MENU_MANAGE")
public class MenuAuthDetail extends CmnBaseCUDEntity {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "COM_CD", nullable = false, length = 50)
    private String comCd;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "MENU_ID", nullable = false, length = 50)
    private String menuId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "USER_ID", nullable = false, length = 50)
    private String userId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "MENU_DETAIL_ID", nullable = false, length = 50)
    private String menuDetailId;

    @jakarta.validation.constraints.Size(max = 1)
    @ColumnDefault("'Y'")
    @Column(name = "USE_FLAG", length = 1)
    private String useFlag;

}