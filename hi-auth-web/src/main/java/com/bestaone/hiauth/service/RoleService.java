package com.bestaone.hiauth.service;

import com.bestaone.hiauth.core.service.BaseService;
import com.bestaone.hiauth.domain.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, Long> {

    List<Role> findByUserId(Long userId);

    void addRoleResources(Long roleId, List<Long> resourcesIds);

    void removeRoleResources(Long roleId, List<Long> resourcesIds);

}
