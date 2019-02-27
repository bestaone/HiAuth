package com.bestaone.aiwan.api.user.vo;

import io.swagger.annotations.ApiModelProperty;

public class RoleVo {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "编码")
	private String code;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "描述")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
