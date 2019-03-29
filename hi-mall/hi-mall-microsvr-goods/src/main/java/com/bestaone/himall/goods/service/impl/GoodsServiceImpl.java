package com.bestaone.himall.goods.service.impl;

import com.bestaone.himall.core.mapper.BaseMapper;
import com.bestaone.himall.core.service.BaseServiceImpl;
import com.bestaone.himall.goods.domain.Goods;
import com.bestaone.himall.goods.mapper.GoodsMapper;
import com.bestaone.himall.goods.service.GoodsService;
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
