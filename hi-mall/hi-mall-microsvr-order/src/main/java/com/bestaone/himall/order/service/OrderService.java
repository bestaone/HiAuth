package com.bestaone.himall.order.service;

import com.bestaone.himall.core.service.BaseService;
import com.bestaone.himall.order.domain.Order;
import com.bestaone.himall.order.enums.OrderStatus;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

}
