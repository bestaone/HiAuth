package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.user.domain.Role;
import com.bestaone.aiwan.user.mapper.RoleMapper;
import com.bestaone.aiwan.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Resource
    RoleMapper mapper;

    @Override
    public BaseMapper<Role, Long> getMapper() {
        return mapper;
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return mapper.findByUserId(userId);
    }

}
