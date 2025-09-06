package com.service.menu.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MENU_INFO_DETAIL", schema = "MENU_MANAGE")
public class MenuInfoDetail {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "MENU_DETAIL_ID", nullable = false, length = 50)
    private String menuDetailId;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "MENU_DETAIL_NAME", length = 100)
    private String menuDetailName;

    @Column(name = "SORT_SEQ")
    private Integer sortSeq;

    @jakarta.validation.constraints.Size(max = 1000)
    @Column(name = "REMARK", length = 1000)
    private String remark;

}