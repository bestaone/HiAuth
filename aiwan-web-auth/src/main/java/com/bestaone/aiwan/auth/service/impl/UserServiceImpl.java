package com.bestaone.aiwan.auth.service.impl;

import com.bestaone.aiwan.auth.domain.User;
import com.bestaone.aiwan.auth.mapper.UserMapper;
import com.bestaone.aiwan.auth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public User save(User user) {
        if(user.getId()!=null){
            mapper.update(user);
        } else {
            user.setId(System.currentTimeMillis());
            mapper.insert(user);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public User findByTel(String tel) {
        return mapper.findByTel(tel);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public List<User> findByName(String name) {
        return mapper.findByName("%" + name + "%");
    }

}
