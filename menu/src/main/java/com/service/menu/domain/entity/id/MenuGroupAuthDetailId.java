package com.service.menu.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MenuGroupAuthDetailId implements Serializable {
    public String menuId;

    public String menuDetailId;

    public String groupId;

    public String comCd;


}