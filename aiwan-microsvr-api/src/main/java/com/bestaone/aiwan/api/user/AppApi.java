package com.bestaone.aiwan.api.user;

import com.bestaone.aiwan.api.user.dto.AppDto;
import com.bestaone.aiwan.api.user.vo.AppVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
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

    @ApiOperation(value = "APP查询")
    ApiResponse<PageVo<AppVo>> query(Integer pageNum, Integer pageSize, String name, String clientId);

    @ApiOperation(value = "查询APP详情")
    ApiResponse<AppVo> getInfo(Long id) throws CommonException;

}
