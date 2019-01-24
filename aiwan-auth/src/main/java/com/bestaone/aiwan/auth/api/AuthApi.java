package com.bestaone.aiwan.auth.api;

import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "AuthApi", description = "AuthApi")
public interface AuthApi {

    //authorize、token使用集成的oauth2框架实现

    @ApiOperation(value = "开始认证")
    ApiResponse authorize() throws CommonException;

    @ApiOperation(value = "获取token")
    ApiResponse token() throws CommonException;

    //这个登录自己代码实现

    @ApiOperation(value = "第三方系统（微信、qq、小程序、微博）认证，获取token")
    ApiResponse thirdToken() throws CommonException;

}
