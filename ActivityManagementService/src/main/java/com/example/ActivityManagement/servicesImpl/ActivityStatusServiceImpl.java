package com.example.ActivityManagement.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ActivityManagement.model.ActivityStatus;
import com.example.ActivityManagement.repository.ActivityStatusRepository;
import com.example.ActivityManagement.services.ActivityStatusService;

import jakarta.validation.Valid;

@Service
public class ActivityStatusServiceImpl implements ActivityStatusService {

	@Autowired
    private ActivityStatusRepository activityStatRepo;

    @Override
    public List<ActivityStatus> getAllActivityStatuses() {
//        return activityStatusRepository.findAll();
    	List<ActivityStatus> actStatus = this.activityStatRepo.findAll();
    	return actStatus;
    }

    @Override
    public ActivityStatus getActivityStatusById(Integer id) {
//        return activityStatusRepository.findById(id).orElse(null);
    	
//    	ActivityStatus actStatusId= this.activityStatusRepository.getById(id);
//    	return actStatusId;
    	
    	Optional<ActivityStatus> actStat = this.activityStatRepo.findById(id);
    	if(actStat.isPresent()) {
    		return this.activityStatRepo.getById(id);
    	}
    	return null;
    }
    @Override
	public ActivityStatus saveActivityStatus(@Valid ActivityStatus actStatus) {
//  	return activityStatusRepository.save(activityStatus); 	
    	ActivityStatus actStatusSave = this.activityStatRepo.save( actStatus);
    	return actStatusSave;
    	
    }

    @Override
    public void deleteActivityStatus(Integer id) {
//        activityStatusRepository.deleteById(id);
//    	ActivityStatus deleteStat = this.activityStatRepo.deleteById(id);
    	
    	Optional<ActivityStatus> actStat = this.activityStatRepo.findById(id);
    	if(actStat.isPresent()) {
    		this.activityStatRepo.deleteById(id);
    	}  	
    }

	@Override
	public ActivityStatus updateActivityStatus(int id, ActivityStatus actStat) {
		Optional<ActivityStatus> upActStat= this.activityStatRepo.findById(id);
		
		if(upActStat.isPresent()) {
			
			ActivityStatus stat = upActStat.get();
			
				stat.setStatusName(actStat.getStatusName());
			
		}
		return null;
	}

	
		

    
    
	
}
