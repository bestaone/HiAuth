package com.bestaone.aiwan.api.order;

import com.bestaone.aiwan.api.order.dto.OrderDto;
import com.bestaone.aiwan.api.order.vo.OrderVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;

@Api(value = "UserApi", description = "UserApi")
public interface OrderApi {

    @ApiOperation(value = "创建用户")
    ApiResponse<String> create(OrderDto userDto) throws CommonException;

    @ApiOperation(value = "更新用户")
    ApiResponse update(Long id, OrderDto userDto, BindingResult errors);

    @ApiOperation(value = "删除用户")
    ApiResponse delete(Long id);

    @ApiOperation(value = "用户查询")
    ApiResponse<PageVo<OrderVo>> query(Integer pageNum, Integer pageSize, String status);

    @ApiOperation(value = "查询用户详情")
    ApiResponse<OrderVo> getInfo(Long id) throws CommonException;

}
