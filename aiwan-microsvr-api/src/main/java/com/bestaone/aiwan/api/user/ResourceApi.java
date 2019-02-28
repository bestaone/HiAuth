package com.bestaone.aiwan.api.user;

import com.bestaone.aiwan.api.user.vo.ResourceVo;
import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "ResourceApi", description = "ResourceApi")
public interface ResourceApi {

    @ApiOperation(value = "资源查询")
    ApiResponse<PageVo<ResourceVo>> query(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "资源查询")
    ApiResponse<List<ResourceVo>> getResourceByRoleId(Long roleId);

}
