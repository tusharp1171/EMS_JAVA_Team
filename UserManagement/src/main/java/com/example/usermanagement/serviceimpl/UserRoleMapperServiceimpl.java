package com.example.usermanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.model.UserRoleMapper;
import com.example.usermanagement.repository.UserRoleMapperRepository;
import com.example.usermanagement.service.UserRoleMapperService;

@Service
public class UserRoleMapperServiceimpl implements UserRoleMapperService {
	@Autowired
    private final UserRoleMapperRepository userRoleMapperRepository;

    
    public UserRoleMapperServiceimpl(UserRoleMapperRepository userRoleMapperRepository) {
        this.userRoleMapperRepository = userRoleMapperRepository;
    }

    @Override
    @Transactional
    public UserRoleMapper createUserRoleMapping(UserRoleMapper userRoleMapper) {
       
        return userRoleMapperRepository.save(userRoleMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleMapper> getAllUserRoleMapper() {
        return userRoleMapperRepository.findAll();
    }

	@Override
	public UserRoleMapper getUserRoleMapperById(int id) {
		Optional<UserRoleMapper> obj=userRoleMapperRepository.findById(id);
		if(obj.isPresent())
		{
			UserRoleMapper mapper=obj.get();
			return mapper;
		}
		return null;
	}
}
