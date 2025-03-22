package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.dto.role.RoleAuthDto;
import cn.hiauth.server.entity.Role;
import cn.hiauth.server.entity.RoleAppResource;
import cn.hiauth.server.mapper.RoleAppResourceMapper;
import cn.hiauth.server.mapper.RoleMapper;
import cn.hiauth.server.service.RoleService;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleAppResourceMapper roleAppResourceMapper;

    @Autowired
    private Snowflake snowflake;
    @Autowired
    private Snowflake idGenerator;

    @Override
    public List<Role> findByEmpId(Long empId) {
        return this.baseMapper.findByEmpId(empId);
    }

    @Override
    public void setEmpRole(Long cid, Long empId, Set<Long> roleIds) {
        this.baseMapper.removeEmpRole(empId, roleIds);
        if (roleIds != null && !roleIds.isEmpty()) {
            List<Map<String, Object>> empRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                Map<String, Object> empRole = new java.util.HashMap<>();
                empRole.put("id", idGenerator.nextId());
                empRole.put("cid", cid);
                empRole.put("empId", empId);
                empRole.put("roleId", roleId);
                empRoles.add(empRole);
            });
            this.baseMapper.createEmpRole(empRoles);
        }
    }

    @Override
    public Boolean auth(RoleAuthDto dto) {

        Role role = this.baseMapper.selectById(dto.getRoleId());
        List<RoleAppResource> list = new ArrayList<>();
        // 写入新权限
        if (dto.getAppResourceIds() != null && !dto.getAppResourceIds().isEmpty()) {
            for (Long appResourceId : dto.getAppResourceIds()) {
                RoleAppResource o = new RoleAppResource();
                o.setId(snowflake.nextId());
                o.setCid(role.getCid());
                o.setAppId(dto.getAppId());
                o.setRoleId(dto.getRoleId());
                o.setAppResourceId(appResourceId);
                list.add(o);
            }
            roleAppResourceMapper.auth(list);
        }

        // 删除旧权限
        LambdaQueryWrapper<RoleAppResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleAppResource::getRoleId, dto.getRoleId());
        queryWrapper.eq(RoleAppResource::getAppId, dto.getAppId());
        if (dto.getAppResourceIds() != null && !dto.getAppResourceIds().isEmpty()) {
            queryWrapper.notIn(RoleAppResource::getAppResourceId, dto.getAppResourceIds());
        }
        roleAppResourceMapper.delete(queryWrapper);

        return Boolean.TRUE;
    }

}