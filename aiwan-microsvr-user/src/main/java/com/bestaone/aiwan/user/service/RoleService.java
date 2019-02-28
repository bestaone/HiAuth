package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.user.domain.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, Long> {

    List<Role> findByUserId(Long userId);

    void addRoleResources(Long roleId, List<Long> resourcesIds);

    void removeRoleResources(Long roleId, List<Long> resourcesIds);

}
