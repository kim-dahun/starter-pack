package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuInfoRepository extends JpaRepository<MenuInfo, String> {
}