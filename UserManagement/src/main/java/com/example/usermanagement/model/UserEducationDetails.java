package com.example.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserEducationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@ManyToOne
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	private Users users;
	

	private Long userId;
	
	private String educationTitle;
	private String description;
	private Integer passingYear;

}
