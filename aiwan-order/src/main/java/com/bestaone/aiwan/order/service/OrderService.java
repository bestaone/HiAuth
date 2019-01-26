package com.bestaone.aiwan.order.service;

import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    Order save(Order user);

    Order findById(Long id);

    List<Order> findAll();

    void delete(Long id);

    List<Order> findByStatus(OrderStatus status);

}
