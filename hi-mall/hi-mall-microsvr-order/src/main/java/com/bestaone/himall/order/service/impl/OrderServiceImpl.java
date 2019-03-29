package com.bestaone.himall.order.service.impl;

import com.bestaone.himall.core.mapper.BaseMapper;
import com.bestaone.himall.core.service.BaseServiceImpl;
import com.bestaone.himall.order.domain.Order;
import com.bestaone.himall.order.enums.OrderStatus;
import com.bestaone.himall.order.mapper.OrderMapper;
import com.bestaone.himall.order.service.OrderService;
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
