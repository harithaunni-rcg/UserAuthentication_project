//package com.user.demo.test;
//
//import org.jetbrains.annotations.TestOnly;
//
//import com.user.demo.serviceImpl.AdminUserServiceImplementation;
//
//public class AdminServiceTest {
//
//	@InjectMocks
//	private AdminUserServiceImplementation adminuserService;
//	
//	@BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @TestOnly
//    public void testGetUserDetails() {
//        String userId = "123";
//        String result = userService.getUserDetails(userId);
//        assertEquals("User details for 123", result);
//    }
//}
