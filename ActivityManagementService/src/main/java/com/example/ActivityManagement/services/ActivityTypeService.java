package com.example.ActivityManagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.ActivityType;


public interface ActivityTypeService {
	
	List<ActivityType> getAllActivityTypes();
    ActivityType getActivityTypeById(Integer id);
    ActivityType saveActivityType(ActivityType activityType);
    void deleteActivityType(Integer id);
	ActivityType updateActivityType(int id, ActivityType activityType);

}
