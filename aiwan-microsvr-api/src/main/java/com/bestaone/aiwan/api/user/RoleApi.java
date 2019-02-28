package com.bestaone.aiwan.api.user;

import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

@Api(value = "RoleApi", description = "RoleApi")
public interface RoleApi {

    @ApiOperation(value = "通过主键查询角色")
    ApiResponse<List<RoleVo>> getRoleByUserId(Long userId);

    @ApiOperation(value = "用户角色")
    ApiResponse<PageVo<RoleVo>> query(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "添加角色的权限")
    ApiResponse addRoleResources(Long roleId, Map params);

    @ApiOperation(value = "移除角色的权限")
    ApiResponse removeRoleResources(Long roleId, Map params);

}
