package com.bestaone.hiauth.api;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.api.commom.PageVo;
import com.bestaone.hiauth.api.vo.ResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "ResourceApi", description = "ResourceApi")
public interface ResourceApi {

    @ApiOperation(value = "资源查询", response = ResourceVo.class)
    ApiResponse<PageVo<ResourceVo>> query(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "资源查询", response = ResourceVo.class)
    ApiResponse<List<ResourceVo>> getResourceByRoleId(Long roleId);

}
