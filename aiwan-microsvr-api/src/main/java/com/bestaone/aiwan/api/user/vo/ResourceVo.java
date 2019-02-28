package com.bestaone.aiwan.api.user.vo;

import com.bestaone.aiwan.api.utils.JsonLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

public class ResourceVo {

	@JsonSerialize(using = JsonLongSerializer.class)
	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "域")
	private String domain;

	@ApiModelProperty(value = "操作")
	private String operate;

	@ApiModelProperty(value = "编码")
	private String code;

	@ApiModelProperty(value = "名称")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
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
}
