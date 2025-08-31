package com.service.user.vo.response;

import com.service.user.entity.UserAuth;
import com.service.user.vo.common.CmnResponseVo;
import com.service.user.vo.data.UserInfoVo;
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
