package com.service.menu.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MenuAuthId implements Serializable {
    public String menuId;

    public String userId;

    public String comCd;


}