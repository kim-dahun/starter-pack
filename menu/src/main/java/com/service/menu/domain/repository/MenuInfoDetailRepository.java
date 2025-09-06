package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuInfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuInfoDetailRepository extends JpaRepository<MenuInfoDetail, String> {
}