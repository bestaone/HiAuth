package com.bestaone.hiauth.api;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.api.commom.PageVo;
import com.bestaone.hiauth.api.dto.UserDto;
import com.bestaone.hiauth.api.vo.AuthUserInfoVo;
import com.bestaone.hiauth.api.vo.UserVo;
import com.bestaone.hiauth.core.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import java.security.Principal;

@Api(value = "UserApi", description = "UserApi")
public interface UserApi {

    @ApiOperation(value = "用户信息", response = AuthUserInfoVo.class)
    ApiResponse<AuthUserInfoVo> profile(Principal principal, Authentication auth);

    @ApiOperation(value = "创建用户")
    ApiResponse<String> create(UserDto userDto) throws CommonException;

    @ApiOperation(value = "更新用户")
    ApiResponse update(Long id, UserDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    ApiResponse delete(Long id);

    @ApiOperation(value = "用户查询", response = UserVo.class)
    ApiResponse<PageVo<UserVo>> query(Integer pageNum, Integer pageSize, String name);

    @ApiOperation(value = "查询用户详情", response = UserVo.class)
    ApiResponse<UserVo> getInfo(Long id) throws CommonException;

}
