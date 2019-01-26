package com.bestaone.aiwan.auth.service;

import com.bestaone.aiwan.auth.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);

    List<User> findByName(String name);

}
