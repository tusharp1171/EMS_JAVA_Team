package com.example.ActivityManagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.ActivityStatus;

import jakarta.validation.Valid;


public interface ActivityStatusService {
	
	List<ActivityStatus> getAllActivityStatuses();
    ActivityStatus getActivityStatusById(Integer id);
    void deleteActivityStatus(Integer id);
	ActivityStatus updateActivityStatus(int id, ActivityStatus actStat);
	ActivityStatus saveActivityStatus(@Valid ActivityStatus actStatus);

}
