package com.user.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.demo.entity.LoginDetails;
import com.user.demo.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  LoginDetails loginuser=loginRepository.findByUsernameAndIsDeletedFalse(username);
		System.out.println("***"+loginuser.getUsername());
		if (loginuser.getUsername().equals(username)) {
	            return org.springframework.security.core.userdetails.User
	                    .withUsername(loginuser.getUsername())
	                    .password(loginuser.getPassword())
	                    .authorities("ADMIN")
	                    .authorities("USER")
	                    .build();
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	}

}
