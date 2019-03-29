package com.bestaone.hiauth.service;

import com.bestaone.hiauth.core.service.BaseService;
import com.bestaone.hiauth.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<User, Long> {

    User findByUsername(String username);

    List<User> findByName(String name);

    User findByTel(String tel);

    void addUserRole(Long userId, Set<Long> roleIds);

    void updateUserRole(Long userId, Set<Long> roleIds);

}
