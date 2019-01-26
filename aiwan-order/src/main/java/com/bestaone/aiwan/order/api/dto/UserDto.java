package com.bestaone.aiwan.order.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	public String getName() {
		return name;
	}

	public UserDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserDto setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserDto setPassword(String password) {
		this.password = password;
		return this;
	}

}
