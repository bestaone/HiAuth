package com.bestaone.himall.api.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderDto {

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "总金额")
	private Float totalAmount;

	public String getTitle() {
		return title;
	}

	public OrderDto setTitle(String title) {
		this.title = title;
		return this;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public OrderDto setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

}
