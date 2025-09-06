package com.service.menu.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MENU_INFO", schema = "MENU_MANAGE")
public class MenuInfo {
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
    @Column(name = "MULTI_INGUAL_CODE", length = 100)
    private String multiIngualCode;

    @Column(name = "CREATE_DATE")
    private Instant createDate;

    @Column(name = "UPDATE_DATE")
    private Instant updateDate;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "CREATE_USER", length = 100)
    private String createUser;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "UPDATE_USER", length = 100)
    private String updateUser;

    @Column(name = "SORT_SEQ")
    private Integer sortSeq;

}