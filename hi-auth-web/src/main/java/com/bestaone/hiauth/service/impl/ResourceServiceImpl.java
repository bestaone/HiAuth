package com.bestaone.hiauth.service.impl;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.core.service.BaseServiceImpl;
import com.bestaone.hiauth.domain.Resource;
import com.bestaone.hiauth.mapper.ResourceMapper;
import com.bestaone.hiauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService {

    @Autowired
    ResourceMapper mapper;

    @Override
    public BaseMapper<Resource, Long> getMapper() {
        return mapper;
    }

    @Override
    public List<Resource> findByUserId(Long userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<Resource> findByRoleId(Long roleId) {
        return mapper.findByRoleId(roleId);
    }

}
