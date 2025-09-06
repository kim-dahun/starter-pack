package com.service.menu.domain.repository;

import com.service.menu.domain.entity.MenuGroupAuthDetail;
import com.service.menu.domain.entity.id.MenuGroupAuthDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupAuthDetailRepository extends JpaRepository<MenuGroupAuthDetail, MenuGroupAuthDetailId> {
}