package com.example.usermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class UserCredential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Email
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@NotNull
	//@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
	@Column(name = "MobileNumber", nullable = false, unique = true)
	private String mobileNumber;
	
	@NotNull
	@Size(min = 8, message = "Password should have at least 8 characters")
	@Column(name = "Password", nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Users user;
}
