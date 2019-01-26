package com.bestaone.aiwan.auth.service;

import com.bestaone.aiwan.auth.domain.Resource;

import java.util.List;

public interface ResourceService {

    Resource save(Resource resource);

    Resource findById(Long id);

    List<Resource> findAll();

    void delete(Long id);

}
