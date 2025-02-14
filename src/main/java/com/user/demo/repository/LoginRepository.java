package com.user.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.demo.entity.LoginDetails;
import com.user.demo.entity.UserDetails;
import java.util.List;


public interface LoginRepository extends JpaRepository<LoginDetails, Long>{

	LoginDetails findByUsernameAndIsDeletedFalse(String username);
	
	LoginDetails findByUsernameAndIsDeletedTrue(String username);

	@Query("update LoginDetails u set u.isDeleted = true where u.userId.id=?1and u.username=?2")
	LoginDetails updateUserStatus(Long id, String email);
}
