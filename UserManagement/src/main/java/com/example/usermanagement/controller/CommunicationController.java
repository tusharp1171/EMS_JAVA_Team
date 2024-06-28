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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.usermanagement.dto.Activitydto;
import com.example.usermanagement.dto.CommunicationLog;
import com.example.usermanagement.dto.CouseEnquriymapDTO;
import com.example.usermanagement.dto.EnquiryDto;



@RestController
@RequestMapping("comunication")
public class CommunicationController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{userid}")
    public ResponseEntity<CouseEnquriymapDTO> getCourseEnquiryDetails(@PathVariable("userid") long salesRepresentativeId) {
        System.out.println("Received request for sales representative ID: " + salesRepresentativeId);

        try {
            ResponseEntity<CommunicationLog> com = restTemplate.getForEntity(
                    "http://192.168.1.126:8084/api/communication-logs/user/" + salesRepresentativeId,
                    CommunicationLog.class);

            if (!com.getStatusCode().is2xxSuccessful()) {
                System.err.println("Failed to retrieve communication log for user ID: " + salesRepresentativeId);
                return ResponseEntity.status(com.getStatusCode()).build();
            }

            CommunicationLog communication = com.getBody();
            int activityId = communication.getActivityId();
            int enquiryId = communication.getEnquiryId();

            ResponseEntity<Activitydto> activity = restTemplate.getForEntity(
                    "http://192.168.1.116:8082/api/activities/readActivityById/" + activityId, Activitydto.class);

            if (!activity.getStatusCode().is2xxSuccessful()) {
                System.err.println("Failed to retrieve activity for activity ID: " + activityId);
                return ResponseEntity.status(activity.getStatusCode()).build();
            }

            ResponseEntity<EnquiryDto> enquiry = restTemplate
                    .getForEntity("http://192.168.1.110:8083/enquiries/" + enquiryId, EnquiryDto.class);

            if (!enquiry.getStatusCode().is2xxSuccessful()) {
                System.err.println("Failed to retrieve enquiry for enquiry ID: " + enquiryId);
                return ResponseEntity.status(enquiry.getStatusCode()).build();
            }

            CouseEnquriymapDTO couseEnquriymapDTO = new CouseEnquriymapDTO();
            couseEnquriymapDTO.setActivitydto(activity.getBody());
            couseEnquriymapDTO.setEnquiryDto(enquiry.getBody());

            return ResponseEntity.ok(couseEnquriymapDTO);

        } catch (HttpClientErrorException e) {
            System.err.println("Client error while retrieving data: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (HttpServerErrorException e) {
            System.err.println("Server error while retrieving data: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (ResourceAccessException e) {
            System.err.println("Resource access error while retrieving data: " + e.getMessage());
            return ResponseEntity.status(503).body(null); 
        } catch (Exception e) {
            System.err.println("Unexpected error while retrieving data: " + e.getMessage());
            return ResponseEntity.status(500).body(null); 
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<CouseEnquriymapDTO>> getalldata() {
        try {
            ResponseEntity<CommunicationLog[]> response = restTemplate.getForEntity(
                    "http://192.168.1.126:8084/api/communication-logs/sales-representative", CommunicationLog[].class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                System.err.println("Failed to retrieve communication logs");
                return ResponseEntity.status(response.getStatusCode()).build();
            }

            CommunicationLog[] communicationLogsArray = response.getBody();
            List<CommunicationLog> communicationLogs = Arrays.asList(communicationLogsArray);

            List<CouseEnquriymapDTO> finalCommunication = new ArrayList<>();

            for (CommunicationLog cLog : communicationLogs) {
                try {
                    int activityId = cLog.getActivityId();
                    int enquiryId = cLog.getEnquiryId();

                    ResponseEntity<Activitydto> activityResponse = restTemplate.getForEntity(
                            "http://192.168.1.116:8082/api/activities/readActivityById/" + activityId, Activitydto.class);

                    if (!activityResponse.getStatusCode().is2xxSuccessful()) {
                        System.err.println("Failed to retrieve activity for activity ID: " + activityId);
                        continue;
                    }

                    ResponseEntity<EnquiryDto> enquiryResponse = restTemplate
                            .getForEntity("http://192.168.1.110:8083/enquiries/" + enquiryId, EnquiryDto.class);

                    if (!enquiryResponse.getStatusCode().is2xxSuccessful()) {
                        System.err.println("Failed to retrieve enquiry for enquiry ID: " + enquiryId);
                        continue;
                    }

                    EnquiryDto enquiryDto = enquiryResponse.getBody();
                    CouseEnquriymapDTO couseEnquriymapDTO = new CouseEnquriymapDTO();
                    couseEnquriymapDTO.setActivitydto(activityResponse.getBody());
                    couseEnquriymapDTO.setEnquiryDto(enquiryDto);

                    finalCommunication.add(couseEnquriymapDTO);
                } catch (HttpClientErrorException e) {
                    System.err.println("Client error while retrieving data: " + e.getMessage());
                } catch (HttpServerErrorException e) {
                    System.err.println("Server error while retrieving data: " + e.getMessage());
                } catch (ResourceAccessException e) {
                    System.err.println("Resource access error while retrieving data: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Unexpected error while retrieving data: " + e.getMessage());
                }
            }

            return ResponseEntity.ok(finalCommunication);

        } catch (HttpClientErrorException e) {
            System.err.println("Client error while retrieving communication logs: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (HttpServerErrorException e) {
            System.err.println("Server error while retrieving communication logs: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (ResourceAccessException e) {
            System.err.println("Resource access error while retrieving communication logs: " + e.getMessage());
            return ResponseEntity.status(503).build(); 
        } catch (Exception e) {
            System.err.println("Unexpected error while retrieving communication logs: " + e.getMessage());
            return ResponseEntity.status(500).build(); 
        }
    }

	@GetMapping("/today")
	public List<CouseEnquriymapDTO> gettodaydata() {
		try {
			ResponseEntity<CommunicationLog[]> response = restTemplate.getForEntity(
					"http://192.168.1.126:8084/api/communication-logs/sales-representative", CommunicationLog[].class);
			CommunicationLog[] communicationLogsArray = response.getBody();
			List<CommunicationLog> communicationLogs = Arrays.asList(communicationLogsArray);

			List<CouseEnquriymapDTO> finalCommunication = new ArrayList<>();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			for (CommunicationLog cLog : communicationLogs) {
				if (cLog.getCommunicationDate().format(formatter).equals(LocalDateTime.now().format(formatter))) {
					int activityId = cLog.getActivityId();
					int enquiryId = cLog.getEnquiryId();

					ResponseEntity<Activitydto> activityResponse = restTemplate.getForEntity(
							"http://192.168.1.116:8082/api/activities/readActivityById/" + activityId,
							Activitydto.class);
					ResponseEntity<EnquiryDto> enquiryResponse = restTemplate
							.getForEntity("http://192.168.1.110:8083/enquiries/" + enquiryId, EnquiryDto.class);
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