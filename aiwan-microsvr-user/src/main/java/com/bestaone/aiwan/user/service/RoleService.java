package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.user.domain.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, Long> {

    List<Role> findByUserId(Long userId);

}
