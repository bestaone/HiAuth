package com.bestaone.aiwan.user.api;

import com.bestaone.aiwan.user.api.dto.UserDto;
import com.bestaone.aiwan.user.api.vo.ApiResponse;
import com.bestaone.aiwan.user.api.vo.Page;
import com.bestaone.aiwan.user.api.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "UserApi", description = "UserApi")
public interface UserApi {

    @ApiOperation(value = "创建用户")
    ApiResponse<String> create(UserDto userDto);

    @ApiOperation(value = "更新用户")
    ApiResponse update(@PathVariable Long id, UserDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    ApiResponse delete(Long id);

    @ApiOperation(value = "用户查询")
    ApiResponse<Page<UserVo>> query(Pageable pageable, UserDto userDto);

    @ApiOperation(value = "查询用户详情")
    ApiResponse<UserVo> getInfo(Long id);

}
