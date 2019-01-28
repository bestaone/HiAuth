package com.bestaone.aiwan.api.misc.dto;

public class FileInfoDto {
	
	public FileInfoDto(String path){
		this.path = path;
	}
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
