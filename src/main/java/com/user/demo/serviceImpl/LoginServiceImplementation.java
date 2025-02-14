package com.user.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.user.demo.config.JwtTokenProvider;
import com.user.demo.dto.ExceptionResponse;
import com.user.demo.dto.LoginDto;
import com.user.demo.dto.LoginResponseDto;
import com.user.demo.entity.LoginDetails;
import com.user.demo.entity.UserRole;
import com.user.demo.repository.LoginRepository;
import com.user.demo.repository.UserDetailsRepository;
import com.user.demo.service.LoginService;

@Service
public class LoginServiceImplementation implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginRepository userDetailsRepository;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public LoginResponseDto getLoginCredentials(LoginDto requestDto) throws Exception {
		LoginResponseDto loginResponse = new LoginResponseDto();
		if (requestDto.getUsername() != null && requestDto.getPassword() != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());
			if (passwordEncoder.matches(requestDto.getPassword(), userDetails.getPassword())) {
				try {
					Authentication authentication = new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
							requestDto.getPassword(), userDetails.getAuthorities());
					authenticationManager.authenticate(authentication);
				} catch (AuthenticationException e) {
					throw new ExceptionResponse(500, "Invalid Username or Password", new Date());
				}
			} else {
				throw new AuthenticationException("Invalid username or password") {
				};
			}
			LoginDetails logindata = userDetailsRepository.findByUsernameAndIsDeletedFalse(requestDto.getUsername());
			if (logindata == null) {
				throw new ExceptionResponse(500, "User not found", new Date());
			} else {

				if (logindata.getUserRole() == UserRole.USER || logindata.getUserRole() == UserRole.ADMIN) {
					loginResponse.setToken(jwtTokenProvider.generateToken(logindata.getUsername()));
					loginResponse.setUserName(logindata.getUsername());
					loginResponse.setUserId(logindata.getUserId().getId());
				} else {
					throw new ExceptionResponse(500, "Unable to create the token", new Date());
				}
			}

		} else {
			throw new ExceptionResponse(500, "Invalid credientials", new Date());

		}
		return loginResponse;
	}

}
