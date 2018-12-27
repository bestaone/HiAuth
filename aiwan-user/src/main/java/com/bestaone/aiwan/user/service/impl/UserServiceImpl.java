package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.user.domain.User;
import com.bestaone.aiwan.user.mapper.UserMapper;
import com.bestaone.aiwan.user.service.UserService;
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
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

}
