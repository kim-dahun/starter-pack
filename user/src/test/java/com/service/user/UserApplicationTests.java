package com.service.user;

import com.service.user.entity.UserAuth;
import com.service.user.entity.UserInfo;
import com.service.user.repository.UserAuthRepository;
import com.service.user.repository.UserInfoRepository;
import org.apache.tomcat.util.buf.UEncoder;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class UserApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Test
//    @Transactional
    void userCreate() {

        UserInfo user = UserInfo.builder()
                .userId("tester")
                .userName("테스터")
                .comCd("111-11-11111")
                .build();

        userInfoRepository.save(user);
        userInfoRepository.flush();

        UserAuth userAuth = UserAuth.builder()
                .userId("tester")
                .userPassword(passwordEncoder.encode("tester"))
                .roles(List.of("USER"))
                .status("Y")
                .comCd("111-11-11111")
//                .userInfo(user)
                .build();


        userAuthRepository.save(userAuth);



    }

}
