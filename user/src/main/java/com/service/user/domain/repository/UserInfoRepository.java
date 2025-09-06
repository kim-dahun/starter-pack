package com.service.user.domain.repository;

import com.service.user.domain.entity.UserInfo;
import com.service.user.domain.entity.id.UserInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, UserInfoId> {
}