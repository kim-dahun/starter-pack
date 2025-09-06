package com.service.menu.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class GroupManageId implements Serializable {
    public String comCd;

    public String groupId;


}