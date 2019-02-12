package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.user.domain.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role findById(Long id);

    List<Role> findAll();

    void delete(Long id);

    List<Role> findByUserId(Long userId);

}
