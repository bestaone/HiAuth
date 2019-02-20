package com.bestaone.aiwan.order.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;
import com.bestaone.aiwan.order.mapper.OrderMapper;
import com.bestaone.aiwan.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    @Resource
    OrderMapper mapper;

    @Override
    public BaseMapper<Order, Long> getMapper() {
        return mapper;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return mapper.findByStatus(status);
    }

}
