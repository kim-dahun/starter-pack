package com.service.user.domain.repository;

import com.service.user.domain.entity.ComInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComInfoRepository extends JpaRepository<ComInfo, String> {
}