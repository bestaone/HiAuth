package com.bestaone.aiwan.auth.api;

import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "AuthApi", description = "AuthApi")
public interface UserApi {

    @ApiOperation(value = "用户登录")
    ApiResponse login(String username, String password) throws CommonException;

    @ApiOperation(value = "用户注册")
    ApiResponse registe(String username, String password) throws CommonException;

}
