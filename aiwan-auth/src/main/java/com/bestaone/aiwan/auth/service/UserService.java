package com.bestaone.aiwan.auth.service;

import com.bestaone.aiwan.auth.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    void delete(Long id);

    List<User> findByName(String name);

    User findByTel(String tel);

}
