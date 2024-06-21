package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.repository.UserTypeRepository;
import com.example.usermanagement.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    @Transactional
    public UserType addUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Override
    @Transactional(readOnly = true)
    public UserType getUserTypeById(Long id) {
        return userTypeRepository.findById(id).orElse(null);
    }

	@Override
	public List<UserType> getAllUserTypes() {
		// TODO Auto-generated method stub
		return userTypeRepository.findAll();
	}

	

	

    
   
}
