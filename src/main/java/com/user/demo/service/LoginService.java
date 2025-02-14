package com.user.demo.service;

import com.user.demo.dto.LoginDto;
import com.user.demo.dto.LoginResponseDto;

public interface LoginService {

	LoginResponseDto getLoginCredentials(LoginDto requestDto) throws Exception;

}
