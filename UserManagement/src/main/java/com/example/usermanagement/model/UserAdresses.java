package com.example.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "userAdresses")
public class UserAdresses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@ManyToOne
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	private Users users;
//	

	private Long userId;
	
	@NotBlank(message = "Atleast one Address Compulsury")
	private String addressLineOne;
	
	private String addressLineTwo;
	
	@NotBlank(message = "Country is required")
	private String country;
	
	@NotBlank(message = "state is required")
	private String state;
	@NotBlank(message = "city is required")
	private String city;
	
	@NotBlank(message = "postalCode is required")
	private String postalCode;
}
