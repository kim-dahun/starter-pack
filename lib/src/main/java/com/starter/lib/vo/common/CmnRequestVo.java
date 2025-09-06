package com.starter.lib.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CmnRequestVo {

    private String requestId;
    private String crudFlag;
    private String comCd;
    private String userId;

}
