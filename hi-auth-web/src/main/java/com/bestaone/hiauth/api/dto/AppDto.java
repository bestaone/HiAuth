package com.bestaone.hiauth.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class AppDto {

	@ApiModelProperty(value = "应用名称")
	private String name;

	@ApiModelProperty(value = "Client Id")
	private String clientId;

	@ApiModelProperty(value = "Client Secret")
	private String clientSecret;

	@ApiModelProperty(value = "图标")
	private String img1X;

	@ApiModelProperty(value = "权限范围")
	private List<String> scopes;

	@ApiModelProperty(value = "允许的认证类型")
	private List<String> authorizedGrantTypes;

	@ApiModelProperty(value = "认证完成后，允许的重定向地址列表")
	private List<String> webServerRedirectUris;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}

	public List<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(List<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public List<String> getWebServerRedirectUris() {
		return webServerRedirectUris;
	}

	public void setWebServerRedirectUris(List<String> webServerRedirectUris) {
		this.webServerRedirectUris = webServerRedirectUris;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getImg1X() {
		return img1X;
	}

	public void setImg1X(String img1X) {
		this.img1X = img1X;
	}
}
