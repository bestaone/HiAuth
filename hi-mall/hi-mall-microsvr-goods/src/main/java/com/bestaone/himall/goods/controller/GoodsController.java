package com.bestaone.himall.goods.controller;

import com.bestaone.himall.api.goods.GoodsApi;
import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.api.PageVo;
import com.bestaone.himall.goods.domain.Goods;
import com.bestaone.himall.goods.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController implements GoodsApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GoodsService goodsService;

	@Override
	@GetMapping
	public ApiResponse<PageVo<Goods>> query(Integer pageNum, Integer pageSize) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Goods> goodsList = goodsService.findAll();
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),goodsList));
	}

}
