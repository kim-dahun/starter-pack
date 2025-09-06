package com.service.user.api.vo.response;

import com.service.user.api.vo.data.UserInfoVo;
import com.starter.lib.vo.common.CmnResponseVo;
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
public class ResponseUserAuthVo extends CmnResponseVo {

    private UserInfoVo data;

}
