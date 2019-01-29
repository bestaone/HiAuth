package com.bestaone.aiwan.api.goods.vo;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import java.util.Date;

public class GoodsVo {
	
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
	private Float price;

    @JsonView(OrderSimpleView.class)
    @ApiModelProperty(value = "库存")
    private Integer amount;

	@JsonView(OrderDetailView.class)
	@ApiModelProperty(value = "创建时间")
	@Past(message = "创建时间")
	private Date createTime;

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
