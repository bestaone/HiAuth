package com.bestaone.aiwan.api.order.vo;

import com.bestaone.aiwan.api.order.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import java.util.Date;

public class OrderVo {
	
	public interface OrderSimpleView {};
	public interface OrderDetailView extends OrderSimpleView {};

	@JsonView(OrderSimpleView.class)
	@ApiModelProperty(value = "主键")
	private Long id;

	@JsonView(OrderSimpleView.class)
	@ApiModelProperty(value = "标题")
	private String title;

	@JsonView(OrderSimpleView.class)
	@ApiModelProperty(value = "总金额")
	private Float totalAmount;

	@JsonView(OrderDetailView.class)
	@ApiModelProperty(value = "创建时间")
	@Past(message = "创建时间")
	private Date createTime;

	@JsonView(OrderSimpleView.class)
	@MyConstraint(message = "状态")
	@ApiModelProperty(value = "用户名")
	private String status;

	public Long getId() {
		return id;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
