package com.service.menu.domain.repository;

import com.service.menu.domain.entity.GroupManage;
import com.service.menu.domain.entity.id.GroupManageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupManageRepository extends JpaRepository<GroupManage, GroupManageId> {
}