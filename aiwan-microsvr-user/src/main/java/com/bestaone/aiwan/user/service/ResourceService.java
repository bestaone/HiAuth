package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.user.domain.Resource;

import java.util.List;

public interface ResourceService {

    Resource save(Resource resource);

    Resource findById(Long id);

    List<Resource> findAll();

    void delete(Long id);

    List<Resource> findByUserId(Long userId);

}
