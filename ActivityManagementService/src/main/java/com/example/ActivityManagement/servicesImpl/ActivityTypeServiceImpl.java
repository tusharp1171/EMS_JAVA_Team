package com.example.ActivityManagement.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.ActivityStatus;
import com.example.ActivityManagement.model.ActivityType;
import com.example.ActivityManagement.repository.ActivityTypeRepository;
import com.example.ActivityManagement.services.ActivityTypeService;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {

	@Autowired
    private ActivityTypeRepository activityTypeRepository;

    @Override
    public List<ActivityType> getAllActivityTypes() {
//    	return activityTypeRepository.findAll();
    	List<ActivityType> actType = this.activityTypeRepository.findAll();
    	return actType;
    }

    @Override
    public ActivityType getActivityTypeById(Integer id) {
//        return activityTypeRepository.findById(id).orElse(null);
    	Optional<ActivityType> actId = this.activityTypeRepository.findById(id);
    	if(actId.isPresent()) {
    		return this.activityTypeRepository.getById(id);	
    	}   	
    	return null;
    	
    }

    @Override
    public ActivityType saveActivityType(ActivityType activityType) {
//        return activityTypeRepository.save(activityType);
    	ActivityType saveType = this.activityTypeRepository.save(activityType);
    	return saveType;
    }


    @Override
    public void deleteActivityType(Integer id) {
    	activityTypeRepository.deleteById(id);
	    	
	    	Optional<ActivityType> actStat = this.activityTypeRepository.findById(id);
	    	if(actStat.isPresent()) {
	    		this.activityTypeRepository.deleteById(id);
	    	}  	
	    }

    @Override
	public ActivityType updateActivityType(int id, ActivityType activityType) {
			Optional<ActivityType> upActType= this.activityTypeRepository.findById(id);
			
			if(upActType.isPresent()) {
				
				ActivityType type= upActType.get();
				
					type.setTypeName(activityType.getTypeName());
					
					return type;
				
			}
			return null;
		}
	
}
