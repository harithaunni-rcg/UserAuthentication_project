package com.user.demo.service;

import java.util.List;

import com.user.demo.dto.UserDto;
import com.user.demo.dto.UserRequestDto;
import com.user.demo.dto.UserResponseDto;
import com.user.demo.entity.UserDetails;

public interface AdminUserService {
	
	UserResponseDto create(UserRequestDto userDetails);

	UserResponseDto delete(String email);

	List<UserDto> getAllUsers();


}
