package com.bestaone.aiwan.goods.service.impl;

import com.bestaone.aiwan.goods.domain.Goods;
import com.bestaone.aiwan.goods.mapper.GoodsMapper;
import com.bestaone.aiwan.goods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsMapper mapper;

    @Override
    public Goods save(Goods order) {
        if(order.getId()!=null){
            mapper.update(order);
        } else {
            order.setId(System.currentTimeMillis());
            mapper.insert(order);
        }
        return order;
    }

    @Override
    public Goods findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Goods> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

}
