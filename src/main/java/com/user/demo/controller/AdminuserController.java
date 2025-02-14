package com.user.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.UserDto;
import com.user.demo.dto.UserResponseDto;
import com.user.demo.entity.UserDetails;
import com.user.demo.service.AdminUserService;
import com.user.demo.serviceImpl.AdminUserServiceImplementation;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/user")
public class AdminuserController {
	
	
	@Autowired
	private AdminUserService adminUserService;
	
	
	@GetMapping
	List<UserDto> getAllUsers() {
		return adminUserService.getAllUsers();
	}
	
	@DeleteMapping("/{email}")
	UserResponseDto delete(@PathVariable(value = "email") String email) throws Exception{
		return adminUserService.delete(email);
	}

}
