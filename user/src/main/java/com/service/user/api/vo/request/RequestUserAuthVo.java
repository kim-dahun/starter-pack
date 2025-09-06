package com.service.user.api.vo.request;

import com.service.user.api.vo.data.UserInfoVo;
import com.starter.lib.vo.common.CmnRequestVo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RequestUserAuthVo extends CmnRequestVo {

    private String userName;
    private String userPassword;
    private String status;

    private UserInfoVo userInfoVo;

}
