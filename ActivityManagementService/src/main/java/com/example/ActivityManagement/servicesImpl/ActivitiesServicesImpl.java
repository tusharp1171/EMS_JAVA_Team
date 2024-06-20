package com.example.ActivityManagement.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.Activities;
import com.example.ActivityManagement.repository.ActivitiesRepo;
import com.example.ActivityManagement.services.ActivitiesService;

@Service
public class ActivitiesServicesImpl implements ActivitiesService {
	

	@Autowired
	ActivitiesRepo repo;

	@Override
    public List<Activities> getAllActivities() {
		List<Activities > list = this.repo.findAll();
        return list;
    }

    @Override
    public Activities getActivityById(Integer id) {
//        return repo.findById(id).orElse(null);
    	
//    	Activities act = this.repo.getById(id);
//    	return act;
    	
    	Optional<Activities> act = this.repo.findById(id);
    	if(act.isPresent()) {
    		return this.repo.getById(id);
    	}
    	return null;
    	
    	
    }

    @Override
    public Activities saveActivity(Activities activity) {
//        return repo.save(activity);
    	
    	Activities actSave = this.repo.save(activity);
    	
    	return actSave;
    }

    
    @Override
    public Activities deleteActivity(Integer id) {
    	Optional<Activities> activity = this.repo.findById(id);
    	
    	if(activity.isPresent()) {
    		repo.deleteById(id);
    	}
    	return null;
    }

	@Override
	public Activities updateActivity(int id, Activities act) {
		
		Optional<Activities> activity = this.repo.findById(id);
		
		if(activity.isPresent()) {
			
			Activities act2 = activity.get();
			
			act2.setActivityStatus(act.getActivityStatus());
			act2.setActivityType(act.getActivityType());
			act2.setDueDate(act.getDueDate());
			act2.setSalesRepresentativeId(act.getSalesRepresentativeId());
			act2.setSummary(act.getSummary());
			
			return repo.save(act2);
			
		}
		
		
		return null;
	}

	
	
}
