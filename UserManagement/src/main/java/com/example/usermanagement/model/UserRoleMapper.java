package com.example.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "userRoleMapper")
public class UserRoleMapper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//@OneToMany
//@JoinColumn(name = "userId")
//private Users users;

//private Role role;
}
