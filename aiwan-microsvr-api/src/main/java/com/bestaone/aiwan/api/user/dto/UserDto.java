package com.bestaone.aiwan.api.user.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public class UserDto {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "手机号码")
	private String tel;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "性别")
	private String gender;

	@ApiModelProperty(value = "角色")
	private Set<Long> roleIds;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGender() {
		return gender;
	}

	public UserDto setGender(String gender) {
		this.gender = gender;
		return this;
	}

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

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
