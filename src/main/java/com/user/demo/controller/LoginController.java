package com.user.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.ExceptionResponse;
import com.user.demo.dto.LoginDto;
import com.user.demo.dto.UserRequestDto;
import com.user.demo.dto.UserResponseDto;
import com.user.demo.entity.StatusResponse;
import com.user.demo.service.AdminUserService;
import com.user.demo.service.LoginService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/public")
public class LoginController {
	
	@Autowired
	LoginService login_service;
	
	@Autowired
	private AdminUserService adminUserService;
	
	
	
	@PostMapping(value = ("/loginCredential"))
	public StatusResponse getLoginCredentials(@RequestBody LoginDto requestDto)throws Exception {
		StatusResponse response = new StatusResponse();
		try {
			response = new StatusResponse("Success", 200,login_service.getLoginCredentials(requestDto));
		}  catch (Exception exception) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(500, exception.getMessage(), new Date());
			response = new StatusResponse("FAILURE", 500, exceptionResponse);
		}
		return response;
	}
	
	@PostMapping(value = "/registration")
	UserResponseDto create(@Valid @RequestBody UserRequestDto user) {
		return adminUserService.create(user);
	}

}
