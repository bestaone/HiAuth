package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.user.domain.Resource;
import com.bestaone.aiwan.user.mapper.ResourceMapper;
import com.bestaone.aiwan.user.service.ResourceService;
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

}
