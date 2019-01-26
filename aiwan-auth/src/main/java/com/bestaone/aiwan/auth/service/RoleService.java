package com.bestaone.aiwan.auth.service;

import com.bestaone.aiwan.auth.domain.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role findById(Long id);

    List<Role> findAll();

    void delete(Long id);

}
