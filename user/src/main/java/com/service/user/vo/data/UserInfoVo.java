package com.service.user.vo.data;

import com.service.user.entity.UserAuth;
import com.service.user.entity.UserInfo;
import jakarta.persistence.*;
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

    private String userName;
    private String phone;
    private String email;
    private String userType;
    private String birthDay;

    public UserInfoVo(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.comCd = userInfo.getComCd();
        this.userName = userInfo.getUserName();
        this.phone = userInfo.getPhone();
        this.email = userInfo.getEmail();
        this.userType = userInfo.getUserType();
        this.birthDay = userInfo.getBirthDay();

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
