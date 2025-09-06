package com.service.user.api.vo.data;

import com.service.user.domain.entity.UserAuth;
import com.service.user.domain.entity.UserInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoVo {

    private String userId;
    private String comCd;

    private String comNm;

    private String userPassword;

    private String userName;
    private String phone;
    private String email;
    private String userType;
    private String birthDay;

    private String status;

    public UserInfoVo(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.comCd = userInfo.getComCd();
        this.userName = userInfo.getUserName();
        this.phone = userInfo.getPhone();
        this.email = userInfo.getEmail();
        this.userType = userInfo.getUserType();
        this.birthDay = userInfo.getBirthDay();

    }

    public UserAuth toAuthEntity() {
        return UserAuth.builder()
                .userId(userId)
                .comCd(comCd)
                .userPassword(userPassword)
                .status(status)
                .build();
    }

    public UserInfo toEntity() {
        return UserInfo.builder()
                .userName(userName)
                .userId(userId)
                .userType(userType)
                .birthDay(birthDay)
                .comCd(comCd)
                .phone(phone)
                .email(email)
                .build();
    }
}
