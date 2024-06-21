package com.example.ActivityManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ActivityManagement.dto.ActivitiesDto;
import com.example.ActivityManagement.model.Activities;


public interface ActivitiesService {

	List<Activities> getAllActivities();
    Activities getActivityById(Integer id);
    Activities saveActivity(Activities activities);
    Activities deleteActivity(Integer id);
	Activities updateActivity(int id, Activities act);


	
	
	
	
}
