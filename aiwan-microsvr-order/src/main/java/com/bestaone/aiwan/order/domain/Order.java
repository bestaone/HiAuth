package com.bestaone.aiwan.order.domain;

import com.bestaone.aiwan.core.entity.BaseEntity;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;

import java.util.Date;

public class Order extends BaseEntity<Long> {

    private Long id;
    private String no;
    private String title;
    private Float totalAmount;
    private OrderStatus status;
    private Date createTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
