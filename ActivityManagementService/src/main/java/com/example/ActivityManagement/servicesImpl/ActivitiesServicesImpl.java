package com.example.ActivityManagement.servicesImpl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ActivityManagement.CustomException.EntityNotFoundException;
import com.example.ActivityManagement.dto.ActivityDto;
import com.example.ActivityManagement.model.Activities;
import com.example.ActivityManagement.model.ActivityStatus;
import com.example.ActivityManagement.model.ActivityType;
import com.example.ActivityManagement.repository.ActivitiesRepo;
import com.example.ActivityManagement.repository.ActivityStatusRepository;
import com.example.ActivityManagement.repository.ActivityTypeRepository;
import com.example.ActivityManagement.services.ActivitiesService;

@Service
public class ActivitiesServicesImpl implements ActivitiesService {
	

	@Autowired
	ActivitiesRepo repo;
	
	@Autowired
	ActivityStatusRepository activityStatusRepository;
	
	@Autowired
	ActivityTypeRepository activityTypeRepository;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
    public List<Activities> getAllActivities() {
        return repo.findAll();
    }

    @Override
    public Activities getActivityById(Integer id) {
    	return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Course type not found with id: " + id));	
    }

    @Override
    public Activities saveActivity(Activities activity) {
//    	return repo.save(activity);
    	Activities actObj = this.repo.save(activity);
    	ActivityDto  newObj = new ActivityDto ();
    		newObj.setActvityId(actObj.getId());
    		System.out.println(actObj.getId());
    		
    		newObj.setActivityStatusId(actObj.getActivityStatus().getId());
    		newObj.setSalesRepresentativeId(actObj.getSalesRepresentativeId());
    		
    		restTemplate.postForEntity("http://192.168.1.126:8084/api/communication-logs/activity", newObj, ActivityDto.class);
    		
    		return actObj;
    	
    		
    		
    	
    }

    
	@Override
	public Activities updateActivity(int id, Activities act) {
		
		  Activities activity= repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Course type not found with id: " + id));

	        activity.setActivityStatus(act.getActivityStatus());
	        activity.setActivityType(act.getActivityType());
	        activity.setDueDate(act.getDueDate());
	        activity.setSalesRepresentativeId(act.getSalesRepresentativeId());
	        act.setSummary(act.getSummary());

	        return repo.save(activity);
		
	}

	@Override
	public void deleteActivity(Integer id) {
		repo.deleteById(id);
	}

	
	
}
