package com.user.demo;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.demo.entity.LoginDetails;

@SpringBootApplication
public class UserAuthenticationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationProjectApplication.class, args);
	}

	
}
