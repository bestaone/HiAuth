package com.bestaone.himall.order.domain;

import com.bestaone.himall.common.entity.BaseEntity;
import com.bestaone.himall.order.enums.OrderStatus;
import com.bestaone.himall.utils.JsonLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class Order extends BaseEntity<Long> {

    @JsonSerialize(using=JsonLongSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String no;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "总金额")
    private Float totalAmount;

    @ApiModelProperty(value = "状态")
    private OrderStatus status;

    @ApiModelProperty(value = "创建时间")
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
