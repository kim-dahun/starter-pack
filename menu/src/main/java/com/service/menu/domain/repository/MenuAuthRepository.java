package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuAuth;
import com.service.menu.domain.entity.MenuAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuAuthRepository extends JpaRepository<MenuAuth, MenuAuthId> {
}