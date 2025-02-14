package com.user.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {

	private String username;
	private String password;
	
	public LoginDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
