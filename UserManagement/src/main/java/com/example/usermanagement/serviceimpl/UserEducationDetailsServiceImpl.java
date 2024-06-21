package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.model.UserEducationDetails;
import com.example.usermanagement.repository.UserEducationDetailsRepository;
import com.example.usermanagement.service.UserEducationDetailsService;

@Service
public class UserEducationDetailsServiceimpl implements UserEducationDetailsService {
	@Autowired
    private final UserEducationDetailsRepository userEducationDetailsRepository;

    
    public UserEducationDetailsServiceimpl(UserEducationDetailsRepository userEducationDetailsRepository) {
        this.userEducationDetailsRepository = userEducationDetailsRepository;
    }

    @Override
    @Transactional
    public UserEducationDetails createEducationDetails(UserEducationDetails userEducation) {
      
        return userEducationDetailsRepository.save(userEducation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEducationDetails> getAllUserEducationDetails() {
        return userEducationDetailsRepository.findAll();
    }
}
