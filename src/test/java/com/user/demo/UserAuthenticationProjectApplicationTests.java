package com.user.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.demo.controller.LoginController;
import com.user.demo.dto.UserRequestDto;
import com.user.demo.dto.UserResponseDto;
import com.user.demo.entity.UserDetails;
import com.user.demo.service.AdminUserService;
import com.user.demo.service.LoginService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LoginController.class)
class UserAuthenticationProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService loginService;

	@Autowired
	private AdminUserService adminUserService;

	@Test
    public void testRegister() throws Exception {
    	UserRequestDto user = new UserRequestDto();
        user.setFirstName("John");
        user.setLastName("Paul");
        user.setPassword("password123");
        user.setEmail("john.paul@example.com");
        user.setGender("male");
        
        UserResponseDto registeredUser = new UserResponseDto("Success", "Tested user creation successfully");
        
        when(adminUserService.create(any(UserRequestDto.class))).thenReturn(registeredUser);

        mockMvc.perform(post("/public/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
    }

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
