package com.bestaone.hiauth.service;

import com.bestaone.hiauth.core.service.BaseService;
import com.bestaone.hiauth.domain.Resource;

import java.util.List;

public interface ResourceService extends BaseService<Resource, Long> {

    List<Resource> findByUserId(Long userId);

    List<Resource> findByRoleId(Long roleId);

}
