package com.user.demo.serviceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.user.demo.dto.ExceptionResponse;
import com.user.demo.dto.UserDto;
import com.user.demo.dto.UserRequestDto;
import com.user.demo.dto.UserResponseDto;
import com.user.demo.entity.LoginDetails;
import com.user.demo.entity.UserDetails;
import com.user.demo.entity.UserRole;
import com.user.demo.repository.LoginRepository;
import com.user.demo.repository.UserDetailsRepository;
import com.user.demo.service.AdminUserService;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class AdminUserServiceImplementation implements AdminUserService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginRepository loginRepository;

	private static final String IPIFY_URL = "https://api.ipify.org?format=json";

	public UserResponseDto create(@Valid UserRequestDto userData) {
//		log.info("Starting Api /admin/user to create new user");
		UserDetails user = new UserDetails();
		user.setEmail(userData.getEmail());
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setGender(userData.getGender());
		user.setIsDeleted(false);
		String ipaddress=getUserIPAddress();
		String newstring=formtString(ipaddress);
		user.setIpaddress(newstring);
		user.setUserCountry("india");
		user = userDetailsRepository.save(user);

		LoginDetails logindata = new LoginDetails();
		logindata.setUserId(user);
		logindata.setUsername(user.getEmail());
		logindata.setPassword(passwordEncoder.encode(userData.getPassword()));
		logindata.setUserRole(UserRole.USER);
		logindata.setIsDeleted(false);
		loginRepository.save(logindata);

		if (!userData.equals(null)) {
			return new UserResponseDto("Success", "created User successfully");
		}
		return null;
	}

	private String formtString(String ipaddress) {
		StringBuilder str=new StringBuilder(ipaddress);
		String val=str.substring(7, ipaddress.length()-2);
		System.out.println("before "+ipaddress+" after "+val);
		return val;
	}

	@Override
	public UserResponseDto delete(String email) {
		UserDetails userDetails = userDetailsRepository.findByEmail(email);
		if (userDetails != null) {
			userDetails.setIsDeleted(true);
			userDetailsRepository.save(userDetails);
			loginRepository.updateUserStatus(userDetails.getId(), userDetails.getEmail());
			log.info("Deleted user with email " + email);
		} else {
			log.info("User with ID " + email + " was not found.");
			throw new ExceptionResponse(200, email + "id is not found", new Date());
		}

		return new UserResponseDto("200", "Success");

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userlist = userDetailsRepository.findByIsDeletedFalse().stream().map(user -> {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setGender(user.getGender());
			return dto;
		}).collect(Collectors.toList());

		return userlist;
	}
	
	String getUserIPAddress() {
		OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(IPIFY_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return responseBody;
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}

}
