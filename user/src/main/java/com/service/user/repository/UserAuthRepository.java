package com.service.user.repository;

import com.service.user.entity.UserAuth;
import com.service.user.entity.id.UserInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, UserInfoId> {
}