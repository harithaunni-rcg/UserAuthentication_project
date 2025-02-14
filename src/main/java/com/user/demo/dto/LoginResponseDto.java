package com.user.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginResponseDto {

	private String token;

	private Long userId;
	private String userName;
	private String role;

	public LoginResponseDto(String token, Long userId, String userName, String role) {
		super();
		this.token = token;
		this.userId = userId;
		this.role = role;
		this.userName = userName;
	}

	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
