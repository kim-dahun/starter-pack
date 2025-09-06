package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuAuthDetail;
import com.service.menu.domain.entity.id.MenuAuthDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuAuthDetailRepository extends JpaRepository<MenuAuthDetail, MenuAuthDetailId> {
}