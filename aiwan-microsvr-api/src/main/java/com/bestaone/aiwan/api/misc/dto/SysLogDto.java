package com.bestaone.aiwan.api.misc.dto;

import io.swagger.annotations.ApiModelProperty;

public class SysLogDto {

	@ApiModelProperty(value = "内容")
	private String content;

	public String getContent() {
		return content;
	}

	public SysLogDto setContent(String content) {
		this.content = content;
		return this;
	}

}
