package com.user.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String ipaddress;
	
	@NotBlank
	private String userCountry;
	
	private Boolean isDeleted;

}
