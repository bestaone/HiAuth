package com.bestaone.aiwan.misc.api;

import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.exception.CommonException;
import com.bestaone.aiwan.misc.api.dto.SysLogDto;
import com.bestaone.aiwan.misc.domain.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "SysLogApi", description = "SysLogApi")
public interface SysLogApi {

    @ApiOperation(value = "创建")
    ApiResponse<String> create(SysLogDto sysLogDto) throws CommonException;

    @ApiOperation(value = "查询")
    ApiResponse<SysLog> getInfo(Long id) throws CommonException;

}
