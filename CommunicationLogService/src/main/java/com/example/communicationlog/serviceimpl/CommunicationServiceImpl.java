package com.example.communicationlog.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.communicationlog.customexception.DataNotFoundException;
import com.example.communicationlog.dto.ActivityDto;
import com.example.communicationlog.dto.EnquiryDto;
import com.example.communicationlog.model.CommunicationLog;
import com.example.communicationlog.repository.CommunicationLogRepository;
import com.example.communicationlog.service.CommunicationService;
import com.example.communicationlog.service.EquiryMicroserviceApi;
import com.example.communicationlog.service.SyallbusMicroserviceApi;
import com.example.communicationlog.service.UserMicroserviceApi;

@Service
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	private CommunicationLogRepository communicationLogRepository;

	@Autowired
	private UserMicroserviceApi userApiCall;

	@Autowired
	private SyallbusMicroserviceApi syallbusApiCall;

	@Autowired
	private EquiryMicroserviceApi enquiryApiCall;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<CommunicationLog> getAllCommunicationLogs() {
		return communicationLogRepository.findAll();
	}

	@Override
	public CommunicationLog getCommunicationLogById(Integer id) {
		return communicationLogRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));
	}

	@Override
	public CommunicationLog createCommunicationLog(CommunicationLog communicationLog) {
		return communicationLogRepository.save(communicationLog);
	}

	@Override
	public CommunicationLog updateCommunicationLog(Integer id, CommunicationLog communicationLog) {
		CommunicationLog existingCommunicationLog = communicationLogRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));

		existingCommunicationLog.setEnquiryId(communicationLog.getEnquiryId());
		existingCommunicationLog.setCustomerId(communicationLog.getCustomerId());
		existingCommunicationLog.setActvityStatusId(communicationLog.getActvityStatusId());
		existingCommunicationLog.setSalesRepresentativeId(communicationLog.getSalesRepresentativeId());
		existingCommunicationLog.setCommunicationDate(communicationLog.getCommunicationDate());

		return communicationLogRepository.save(existingCommunicationLog);
	}

	@Override
	public void deleteCommunicationLog(Integer id) {
		CommunicationLog existingCommunicationLog = communicationLogRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));
		communicationLogRepository.delete(existingCommunicationLog);
	}

	@Override
	public CommunicationLog getAllCommunicationData(Integer userId) {
		getEnquiryData(userId);
		return null;
	}

	public void getEnquiryData(Integer userId) {
		ResponseEntity<EnquiryDto> enquiry = this.restTemplate
				.getForEntity(this.enquiryApiCall.getEnquiryUrl() + "getByUserId/" + userId, EnquiryDto.class);
	}

	public void getActivityData(Integer userId) {
		ResponseEntity<ActivityDto> activity = this.restTemplate.getForEntity(null, ActivityDto.class);

	}

	@Override
	public Optional<CommunicationLog> findCommunicationDataByUserId(long salesRepresentativeId) {
	    return this.communicationLogRepository.findBySalesRepresentativeId(salesRepresentativeId);
	}
	
	@Override
	public List<CommunicationLog> findCommunicationLogsBySalesRepresentativeId() {
        return this.communicationLogRepository.findAll();
    }
/*	
	@Override
	public CommunicationLog createCommLogWithEnquiry(EnquiryDto enqObj) {
	    try {
	        System.out.println(enqObj);

	        // Always create a new CommunicationLog object
	        CommunicationLog saveObj = new CommunicationLog();
	        saveObj.setEnquiryId(enqObj.getEnquiryId());
	        saveObj.setSalesRepresentativeId(enqObj.getSalesPersonId().longValue());
	        saveObj.setCustomerId(enqObj.getSalesPersonId());
	        saveObj.setCommunicationDate(LocalDateTime.now());

	        return this.communicationLogRepository.save(saveObj);
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to create communication log with enquiry", e);
	    }
	}
	
	@Override
	public CommunicationLog createCommunicationLogWithActivity(ActivityDto actObj) {
	    try {
	        System.out.println(actObj);

	        // Always create a new CommunicationLog object
	        CommunicationLog saveObj = new CommunicationLog();
	        saveObj.setActivityId(actObj.getActvityId());
	        saveObj.setActvityStatusId(actObj.getActivityStatusId());
	        saveObj.setCustomerId(actObj.getSalesRepresentativeId());
	        saveObj.setSalesRepresentativeId(actObj.getSalesRepresentativeId().longValue());
	        saveObj.setCommunicationDate(LocalDateTime.now());

	        return this.communicationLogRepository.save(saveObj);
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to create communication log with activity", e);
	    }
	}
	
*/
	@Override
	public CommunicationLog createCommLogWithEnquiry(EnquiryDto enqObj) {
		Optional<CommunicationLog> commObj = null;
		try {
			commObj = this.communicationLogRepository.findByEnquiryId(enqObj.getEnquiryId());
			if (commObj.isPresent()) {
				CommunicationLog updateObj = commObj.get();
				updateObj.setEnquiryId(enqObj.getEnquiryId());
				updateObj.setCommunicationDate(LocalDateTime.now());
				return this.communicationLogRepository.save(updateObj);
			} 
			else {
				CommunicationLog saveObj = new CommunicationLog();
				saveObj.setEnquiryId(enqObj.getEnquiryId());
				saveObj.setSalesRepresentativeId((long) enqObj.getSalesPersonId());
				saveObj.setCommunicationDate(LocalDateTime.now());
				return this.communicationLogRepository.save(saveObj);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to create communication log with enquiry", e);
		}
	}

	@Override
	public CommunicationLog createCommunicationLogWithActivity(ActivityDto actObj) {
		Optional<CommunicationLog> commObj = null;
		try {
			System.out.println(actObj);
			 commObj = this.communicationLogRepository.findByActivityId(actObj.getActvityId());
			if (commObj.isPresent()) {
				CommunicationLog updateObj = commObj.get();
				updateObj.setActivityId(actObj.getActvityId());
				updateObj.setActvityStatusId(actObj.getActivityStatusId());
				updateObj.setCustomerId(actObj.getSalesRepresentativeId());
				updateObj.setCommunicationDate(LocalDateTime.now());
				
				return this.communicationLogRepository.save(updateObj);
			} 
			else {
				CommunicationLog saveObj = new CommunicationLog();
				saveObj.setActivityId(actObj.getActvityId());
				saveObj.setActvityStatusId(actObj.getActivityStatusId());
				saveObj.setCustomerId(actObj.getSalesRepresentativeId());
				saveObj.setSalesRepresentativeId((long)actObj.getSalesRepresentativeId());
				saveObj.setCommunicationDate(LocalDateTime.now());
				return this.communicationLogRepository.save(saveObj);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to create communication log with activity", e);
		}
	}
	
}
