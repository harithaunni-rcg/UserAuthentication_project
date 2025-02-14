package com.user.demo.dto;

import lombok.Data;

@Data
public class UserResponseDto {

	private String status;

	private String message;
	
	public UserResponseDto(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


}
