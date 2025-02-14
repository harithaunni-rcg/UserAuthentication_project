package com.user.demo.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String password;
}
