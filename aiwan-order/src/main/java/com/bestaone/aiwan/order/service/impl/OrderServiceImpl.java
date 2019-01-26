package com.bestaone.aiwan.order.service.impl;

import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;
import com.bestaone.aiwan.order.mapper.OrderMapper;
import com.bestaone.aiwan.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper mapper;

    @Override
    public Order save(Order order) {
        if(order.getId()!=null){
            mapper.update(order);
        } else {
            order.setId(System.currentTimeMillis());
            mapper.insert(order);
        }
        return order;
    }

    @Override
    public Order findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return mapper.findByStatus(status);
    }

}
