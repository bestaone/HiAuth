package com.bestaone.aiwan.user.api;

import com.bestaone.aiwan.user.api.dto.UserDto;
import com.bestaone.aiwan.user.api.vo.PageVo;
import com.bestaone.aiwan.user.api.vo.UserVo;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "UserApi", description = "UserApi")
public interface UserApi {

    @ApiOperation(value = "创建用户")
    String create(UserDto userDto);

    @ApiOperation(value = "更新用户")
    void update(@PathVariable Long id, UserDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    void delete(Long id);

    @ApiOperation(value = "用户查询")
    PageVo<UserVo> query(Pageable pageable, UserDto userDto);

    @ApiOperation(value = "查询用户详情")
    @JsonView(UserVo.UserSimpleView.class)
    UserVo getInfo(Long id);

}
