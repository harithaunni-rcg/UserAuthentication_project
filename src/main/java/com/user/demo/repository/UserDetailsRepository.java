package com.user.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.demo.entity.LoginDetails;
import com.user.demo.entity.UserDetails;
import java.util.List;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	UserDetails findByEmail(String email);

	List<UserDetails> findByIsDeletedFalse();
}
