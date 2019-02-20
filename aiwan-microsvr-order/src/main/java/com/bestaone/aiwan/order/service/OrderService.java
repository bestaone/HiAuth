package com.bestaone.aiwan.order.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

}
