package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserEducationDetails;

public interface UserEducationDetailsService {

	UserEducationDetails createEducationDetails(UserEducationDetails userEducation);

	List<UserEducationDetails> getAllUserEducationDetails();

}
