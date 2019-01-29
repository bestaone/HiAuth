package com.bestaone.aiwan.goods.web.controller;

import com.bestaone.aiwan.api.goods.GoodsApi;
import com.bestaone.aiwan.api.goods.vo.GoodsVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.goods.domain.Goods;
import com.bestaone.aiwan.goods.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController implements GoodsApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GoodsService goodsService;

	@Override
	@GetMapping
	public ApiResponse<PageVo<GoodsVo>> query(Integer pageNum, Integer pageSize) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Goods> goodsList = goodsService.findAll();
		List<GoodsVo> orderVos = new ArrayList<>();
		for(Goods goods : goodsList){
			GoodsVo vo = new GoodsVo();
			vo.setTitle(goods.getTitle());
			vo.setId(goods.getId());
			vo.setPrice(goods.getPrice());
			vo.setAmount(goods.getAmount());
			orderVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),orderVos));
	}

}
