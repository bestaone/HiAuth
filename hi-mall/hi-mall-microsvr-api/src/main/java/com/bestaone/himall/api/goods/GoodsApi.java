package com.bestaone.himall.api.goods;

import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.api.PageVo;
import com.bestaone.himall.goods.domain.Goods;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GoodsApi", description = "GoodsApi")
@Headers({"Content-Type: application/json","Accept: application/json"})
public interface GoodsApi {

    @ApiOperation(value = "查询", response = Goods.class)
    @RequestLine("GET /goods?pageNum={pageNum}&pageSize={pageSize}")
    ApiResponse<PageVo<Goods>> query(
            @Param(value="pageNum") Integer pageNum,
            @Param(value="pageSize") Integer pageSize);

}
