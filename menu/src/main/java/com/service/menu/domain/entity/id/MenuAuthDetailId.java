package com.service.menu.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MenuAuthDetailId implements Serializable {
    public String comCd;

    public String menuId;

    public String userId;

    public String menuDetailId;


}