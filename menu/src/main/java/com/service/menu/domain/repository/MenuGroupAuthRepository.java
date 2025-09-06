package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuGroupAuth;
import com.service.menu.domain.entity.id.MenuGroupAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupAuthRepository extends JpaRepository<MenuGroupAuth, MenuGroupAuthId> {
}