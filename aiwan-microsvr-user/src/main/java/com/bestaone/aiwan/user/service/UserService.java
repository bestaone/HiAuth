package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.user.domain.User;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    User findByUsername(String username);

    List<User> findByName(String name);

    User findByTel(String tel);

}
