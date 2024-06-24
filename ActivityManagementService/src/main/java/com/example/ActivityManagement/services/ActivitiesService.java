package com.example.ActivityManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ActivityManagement.dto.ActivityDto;
import com.example.ActivityManagement.model.Activities;

import jakarta.validation.Valid;


public interface ActivitiesService {

	List<Activities> getAllActivities();
    Activities getActivityById(Integer id);
    Activities saveActivity(@Valid Activities activities);
    void deleteActivity(Integer id);
	Activities updateActivity(int id, Activities act);
	


	
	
	
	
}
