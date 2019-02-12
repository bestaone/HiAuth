package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.user.domain.Role;
import com.bestaone.aiwan.user.mapper.RoleMapper;
import com.bestaone.aiwan.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper mapper;

    @Override
    public Role save(Role role) {
        if(role.getId()!=null){
            mapper.update(role);
        } else {
            role.setId(System.currentTimeMillis());
            mapper.insert(role);
        }
        return role;
    }

    @Override
    public Role findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return mapper.findByUserId(userId);
    }

}
