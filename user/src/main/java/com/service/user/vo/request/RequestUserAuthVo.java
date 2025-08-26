package com.service.user.vo.request;

import com.service.user.entity.UserAuth;
import com.service.user.entity.UserInfo;
import com.service.user.vo.common.CmnRequestVo;
import com.service.user.vo.common.CmnResponseVo;
import com.service.user.vo.data.UserInfoVo;
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
