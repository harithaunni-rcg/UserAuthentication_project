package com.user.demo.dto;

import lombok.Data;

@Data
public class UserRequestDto {

	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String password;
}
