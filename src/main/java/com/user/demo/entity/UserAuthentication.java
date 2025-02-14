package com.user.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserAuthentication {
	
	private boolean applicationType;

	private UserDetails userDetails;

	private String password;
	

}
