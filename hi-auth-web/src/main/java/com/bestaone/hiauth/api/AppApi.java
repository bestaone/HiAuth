package com.bestaone.hiauth.api;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.api.commom.PageVo;
import com.bestaone.hiauth.api.dto.AppDto;
import com.bestaone.hiauth.api.vo.AppVo;
import com.bestaone.hiauth.core.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;

@Api(value = "AppApi", description = "AppApi")
public interface AppApi {

    @ApiOperation(value = "创建APP")
    ApiResponse<String> create(AppDto appDto) throws CommonException;

    @ApiOperation(value = "更新APP")
    ApiResponse update(Long id, AppDto appDto, BindingResult errors);

    @ApiOperation(value = "删除APP")
    ApiResponse delete(Long id);

    @ApiOperation(value = "APP查询", response = AppVo.class)
    ApiResponse<PageVo<AppVo>> query(Integer pageNum, Integer pageSize, String name, String clientId);

    @ApiOperation(value = "查询APP详情", response = AppVo.class)
    ApiResponse<AppVo> getInfo(Long id) throws CommonException;

}
