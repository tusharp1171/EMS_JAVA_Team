package com.example.ActivityManagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.ActivityStatus;


public interface ActivityStatusService {
	
	List<ActivityStatus> getAllActivityStatuses();
    ActivityStatus getActivityStatusById(Integer id);
    ActivityStatus saveActivityStatus(ActivityStatus activityStatus);
    void deleteActivityStatus(Integer id);
	ActivityStatus updateActivityStatus(int id, ActivityStatus actStat);

}
