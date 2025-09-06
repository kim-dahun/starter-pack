package com.service.user.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserInfoId implements Serializable {
    public String userId;

    public String comCd;


}