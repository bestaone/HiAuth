package com.bestaone.aiwan.order.api.dto;

import io.swagger.annotations.ApiModelProperty;

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
