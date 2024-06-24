package com.example.usermanagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.usermanagement.dto.Activitydto;
import com.example.usermanagement.dto.CommunicationLog;
import com.example.usermanagement.dto.CouseEnquriymapDTO;
import com.example.usermanagement.dto.EnquiryDto;

@RestController
@RequestMapping("comunication")
public class CommunicationController{
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("{userid}")
	public CouseEnquriymapDTO getMethodName(@PathVariable("userid") long salesRepresentativeId) {
		System.out.println(salesRepresentativeId);
		
			ResponseEntity<CommunicationLog> com = restTemplate.getForEntity("http://192.168.1.126:8084/api/communication-logs/user/"+salesRepresentativeId, CommunicationLog.class);
			
			if(com.getStatusCode().is2xxSuccessful()) {
				System.out.println("uysiajt");
				CommunicationLog commuication = com.getBody();
				int activityid = commuication.getActivityId();
				int enquryid= commuication.getEnquiryId();
				
				ResponseEntity<Activitydto> activity = restTemplate.getForEntity("http://192.168.1.116:8082/api/activities/readActivityById/"+activityid, Activitydto.class);
				
				ResponseEntity<EnquiryDto> enqury = restTemplate.getForEntity("http://192.168.1.110:8083/enquiries/"+enquryid, EnquiryDto.class);
				
				EnquiryDto enquiry = enqury.getBody();
				
				
				CouseEnquriymapDTO couseEnquriymapDTO=new CouseEnquriymapDTO();
				couseEnquriymapDTO.setActivitydto(activity.getBody());
				couseEnquriymapDTO.setEnquiryDto(enquiry);
				return couseEnquriymapDTO;
			
		}
			return null;
			

	}
	
	@GetMapping("/all")
	public List<CouseEnquriymapDTO> getalldata() {
	    ResponseEntity<CommunicationLog[]> response = restTemplate.getForEntity("http://192.168.1.126:8084/api/communication-logs/sales-representative", CommunicationLog[].class);
	    CommunicationLog[] communicationLogsArray = response.getBody();
	    List<CommunicationLog> communicationLogs = Arrays.asList(communicationLogsArray);

	    List<CouseEnquriymapDTO> finalCommunication = new ArrayList();

	    for (CommunicationLog cLog : communicationLogs) {
	        int activityId = cLog.getActivityId();
	        int enquiryId = cLog.getEnquiryId();

	        ResponseEntity<Activitydto> activityResponse = restTemplate.getForEntity("http://192.168.1.116:8082/api/activities/readActivityById/" + activityId, Activitydto.class);
	        ResponseEntity<EnquiryDto> enquiryResponse = restTemplate.getForEntity("http://192.168.1.110:8083/enquiries/" + enquiryId, EnquiryDto.class);
	        EnquiryDto enquiryDto = enquiryResponse.getBody();
	        CouseEnquriymapDTO couseEnquriymapDTO = new CouseEnquriymapDTO();
	        couseEnquriymapDTO.setActivitydto(activityResponse.getBody());
	        couseEnquriymapDTO.setEnquiryDto(enquiryDto);

	        finalCommunication.add(couseEnquriymapDTO);
	    }

	    return finalCommunication;
	}
	
	@GetMapping("/today")
    public List<CouseEnquriymapDTO> gettodaydata() {
        try {
            ResponseEntity<CommunicationLog[]> response = restTemplate.getForEntity("http://192.168.1.126:8084/api/communication-logs/sales-representative", CommunicationLog[].class);
            CommunicationLog[] communicationLogsArray = response.getBody();
            List<CommunicationLog> communicationLogs = Arrays.asList(communicationLogsArray);

            List<CouseEnquriymapDTO> finalCommunication = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CommunicationLog cLog : communicationLogs) {
                if (cLog.getCommunicationDate().format(formatter).equals(LocalDateTime.now().format(formatter))) {
                    int activityId = cLog.getActivityId();
                    int enquiryId = cLog.getEnquiryId();

                    ResponseEntity<Activitydto> activityResponse = restTemplate.getForEntity("http://192.168.1.116:8082/api/activities/readActivityById/" + activityId, Activitydto.class);
                    ResponseEntity<EnquiryDto> enquiryResponse = restTemplate.getForEntity("http://192.168.1.110:8083/enquiries/" + enquiryId, EnquiryDto.class);
                    EnquiryDto enquiryDto = enquiryResponse.getBody();

                    CouseEnquriymapDTO couseEnquriymapDTO = new CouseEnquriymapDTO();
                    couseEnquriymapDTO.setActivitydto(activityResponse.getBody());
                    couseEnquriymapDTO.setEnquiryDto(enquiryDto);

                    finalCommunication.add(couseEnquriymapDTO);
                }
            }

            return finalCommunication;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching data", e);
        }
    }
	
	
//	@GetMapping("/todayEnqury/{LocalDate.now()}")
//	public List<CommunicationLog> getTodaysEnquryList(@PathVariable LocalDateTime date){
//		 ResponseEntity<List<CommunicationLog>> todaysEnquiryResponse = restTemplate.getForEntity("http://192.168.1.116:8082/api/activities/readActivityById/" + date, CommunicationLog[].class);
//	        
//		return null;
//		
//	}
//	
}