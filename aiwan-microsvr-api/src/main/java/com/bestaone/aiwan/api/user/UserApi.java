package com.bestaone.aiwan.api.user;

import com.bestaone.aiwan.api.user.dto.UserDto;
import com.bestaone.aiwan.api.user.vo.UserVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;

@Api(value = "UserApi", description = "UserApi")
public interface UserApi {

    @ApiOperation(value = "创建用户")
    ApiResponse<String> create(UserDto userDto) throws CommonException;

    @ApiOperation(value = "更新用户")
    ApiResponse update(Long id, UserDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    ApiResponse delete(Long id);

    @ApiOperation(value = "用户查询")
    ApiResponse<PageVo<UserVo>> query(Integer pageNum, Integer pageSize, String name);

    @ApiOperation(value = "查询用户详情")
    ApiResponse<UserVo> getInfo(Long id) throws CommonException;

}
