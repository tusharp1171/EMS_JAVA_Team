package com.example.usermanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.repository.UserTypeRepo;
import com.example.usermanagement.service.UserTypeService;



@Service
public class UserTypeServiceImpl implements UserTypeService{

	@Autowired
	UserTypeRepo userTypeRepo;

	@Override
	public UserType addUserType( UserType userType) {
		
		return userTypeRepo.save(userType);
	}
	
	
}
