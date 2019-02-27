package com.bestaone.aiwan.api.user;

import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import io.swagger.annotations.Api;

import java.util.List;

@Api(value = "RoleApi", description = "RoleApi")
public interface RoleApi {

    ApiResponse<List<RoleVo>> getRoleByUserId(Long userId) throws CommonException;

}
