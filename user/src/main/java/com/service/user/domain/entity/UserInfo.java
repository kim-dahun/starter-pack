package com.service.user.domain.entity;


import com.service.user.domain.entity.id.UserInfoId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(UserInfoId.class)
@Entity
@Table(name = "USER_INFO", schema = "USER_MANAGE")
public class UserInfo extends CmnBaseCUDEntity {
    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "USER_ID", nullable = false, length = 50)
    private String userId;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "COM_CD", nullable = false, length = 50)
    private String comCd;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "USER_NAME", length = 50)
    private String userName;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "USER_TYPE", length = 50)
    private String userType;

    @jakarta.validation.constraints.Size(max = 20)
    @Column(name = "BIRTH_DAY", length = 20)
    private String birthDay;

}