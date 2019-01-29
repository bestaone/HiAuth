package com.bestaone.aiwan.goods.service;

import com.bestaone.aiwan.goods.domain.Goods;

import java.util.List;

public interface GoodsService {

    Goods save(Goods user);

    Goods findById(Long id);

    List<Goods> findAll();

    void delete(Long id);

}
