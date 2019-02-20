package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.user.domain.Resource;

import java.util.List;

public interface ResourceService extends BaseService<Resource, Long>{

    List<Resource> findByUserId(Long userId);

}
