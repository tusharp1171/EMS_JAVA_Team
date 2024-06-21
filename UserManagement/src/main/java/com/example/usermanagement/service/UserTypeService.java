package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.model.Users;

public interface UserTypeService {

	UserType addUserType(UserType userType);

	UserType getUserTypeById(Long id);

	List<UserType> getAllUserTypes();




	

	
}
