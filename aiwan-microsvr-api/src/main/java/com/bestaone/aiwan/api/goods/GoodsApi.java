package com.bestaone.aiwan.api.goods;

import com.bestaone.aiwan.api.goods.vo.GoodsVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GoodsApi", description = "GoodsApi")
public interface GoodsApi {

    @ApiOperation(value = "查询")
    ApiResponse<PageVo<GoodsVo>> query(Integer pageNum, Integer pageSize);

}
