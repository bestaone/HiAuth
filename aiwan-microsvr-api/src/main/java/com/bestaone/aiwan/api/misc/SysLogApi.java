package com.bestaone.aiwan.api.misc;

import com.bestaone.aiwan.api.misc.dto.SysLogDto;
import com.bestaone.aiwan.api.misc.vo.SysLogVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "SysLogApi", description = "SysLogApi")
public interface SysLogApi {

    @ApiOperation(value = "创建")
    ApiResponse<String> create(SysLogDto sysLogDto) throws CommonException;

    @ApiOperation(value = "查询")
    ApiResponse<SysLogVo> getInfo(Long id) throws CommonException;

}
