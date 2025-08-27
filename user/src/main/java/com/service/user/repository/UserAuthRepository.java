package com.service.user.repository;

import com.service.user.entity.UserAuth;
import com.service.user.entity.id.UserInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, UserInfoId> {
    Optional<UserAuth> findByComCdAndUserId(String comCd, String userId);
}