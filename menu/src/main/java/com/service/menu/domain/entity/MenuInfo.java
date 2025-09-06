package com.service.menu.domain.entity;

import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "MENU_INFO", schema = "MENU_MANAGE")
public class MenuInfo extends CmnBaseCUDEntity {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "MENU_ID", nullable = false, length = 50)
    private String menuId;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "MENU_NAME", length = 100)
    private String menuName;

    @Column(name = "MENU_LEVEL")
    private Integer menuLevel;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "PARENT_MENU_ID", length = 50)
    private String parentMenuId;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "LANG_CODE", length = 100)
    private String langCode;

    @Column(name = "SORT_SEQ")
    private Integer sortSeq;

}