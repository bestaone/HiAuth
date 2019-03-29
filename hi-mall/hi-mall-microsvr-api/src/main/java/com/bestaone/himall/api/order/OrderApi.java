package com.bestaone.himall.api.order;

import com.bestaone.himall.api.order.dto.OrderDto;
import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.api.PageVo;
import com.bestaone.himall.common.exception.CommonException;
import com.bestaone.himall.order.domain.Order;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;

@Api(value = "UserApi", description = "UserApi")
@Headers({"Content-Type: application/json","Accept: application/json"})
public interface OrderApi {

    @ApiOperation(value = "创建用户")
    @RequestLine("POST /order")
    ApiResponse<String> create(OrderDto userDto) throws CommonException;

    @ApiOperation(value = "更新用户")
    @RequestLine("PUT /order/{id}")
    ApiResponse update(
            @Param(value="id") Long id,
            @Param(value="userDto") OrderDto userDto,
            @Param(value="errors") BindingResult errors);

    @ApiOperation(value = "删除用户")
    @RequestLine("DELETE /order/{id}")
    ApiResponse delete(@Param(value="id") Long id);

    @ApiOperation(value = "用户查询", response = Order.class)
    @RequestLine("GET /order")
    ApiResponse<PageVo<Order>> query(
            @Param(value="pageNum") Integer pageNum,
            @Param(value="pageSize") Integer pageSize,
            @Param(value="status") String status);

    @ApiOperation(value = "查询用户详情", response = Order.class)
    @RequestLine("GET /order/{id}")
    ApiResponse<Order> getInfo(@Param(value = "id") Long id) throws CommonException;

}
