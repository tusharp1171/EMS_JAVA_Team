package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserRoleMapper;

public interface UserRoleMapperService {

	UserRoleMapper createUserRoleMapping(UserRoleMapper userRoleMapper);

	List<UserRoleMapper> getAllUserRoleMapper();

	UserRoleMapper getUserRoleMapperById(int id);

	
	

}
