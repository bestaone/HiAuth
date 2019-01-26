package com.bestaone.aiwan.order.api;

import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.api.PageVo;
import com.bestaone.aiwan.core.exception.CommonException;
import com.bestaone.aiwan.order.api.dto.OrderDto;
import com.bestaone.aiwan.order.api.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "UserApi", description = "UserApi")
public interface OrderApi {

    @ApiOperation(value = "创建用户")
    ApiResponse<String> create(OrderDto userDto) throws CommonException;

    @ApiOperation(value = "更新用户")
    ApiResponse update(@PathVariable Long id, OrderDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    ApiResponse delete(Long id);

    @ApiOperation(value = "用户查询")
    ApiResponse<PageVo<OrderVo>> query(Integer pageNum, Integer pageSize, String name);

    @ApiOperation(value = "查询用户详情")
    ApiResponse<OrderVo> getInfo(Long id) throws CommonException;

}
