package com.example.usermanagement.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = " firstName is required")
	@Size(min = 2)
	private String firstName;
	
	@NotBlank(message = "lastName is required")
	@Size(min = 2)
	private String lastName;
	private LocalDate dob;
   
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userTypeId", referencedColumnName = "id")
	private UserType userType;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserAdresses> userAdresses;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserCredentials> userCredentials;
 
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserEducationDetails> userEducationDetails;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRoleMapper> userRoleMapper;
}
