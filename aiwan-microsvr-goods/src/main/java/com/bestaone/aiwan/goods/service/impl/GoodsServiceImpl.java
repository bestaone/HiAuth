package com.bestaone.aiwan.goods.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.goods.domain.Goods;
import com.bestaone.aiwan.goods.mapper.GoodsMapper;
import com.bestaone.aiwan.goods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods, Long> implements GoodsService {

    @Resource
    GoodsMapper mapper;

    @Override
    public BaseMapper<Goods, Long> getMapper() {
        return mapper;
    }

}
