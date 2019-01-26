package com.bestaone.aiwan.auth.service.impl;

import com.bestaone.aiwan.auth.domain.Resource;
import com.bestaone.aiwan.auth.mapper.ResourceMapper;
import com.bestaone.aiwan.auth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper mapper;

    @Override
    public Resource save(Resource resource) {
        if(resource.getId()!=null){
            mapper.update(resource);
        } else {
            resource.setId(System.currentTimeMillis());
            mapper.insert(resource);
        }
        return resource;
    }

    @Override
    public Resource findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Resource> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

}
