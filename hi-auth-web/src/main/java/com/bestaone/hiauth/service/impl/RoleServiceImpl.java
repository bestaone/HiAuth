package com.bestaone.hiauth.service.impl;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.core.service.BaseServiceImpl;
import com.bestaone.hiauth.domain.Role;
import com.bestaone.hiauth.domain.RoleResource;
import com.bestaone.hiauth.mapper.RoleMapper;
import com.bestaone.hiauth.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public void addRoleResources(Long roleId, List<Long> resourcesIds) {
        List<RoleResource> rrs = new ArrayList<>();
        for(Long resourcesId : resourcesIds){
            RoleResource rr = new RoleResource();
            rr.setId((Long)idGenerator.generate());
            rr.setRoleId(roleId);
            rr.setResourceId(resourcesId);
            rrs.add(rr);
        }
        mapper.insertRoleResources(rrs);
    }

    @Override
    public void removeRoleResources(Long roleId, List<Long> resourcesIds) {
        mapper.deleteRoleResources(roleId, resourcesIds);
    }

}
