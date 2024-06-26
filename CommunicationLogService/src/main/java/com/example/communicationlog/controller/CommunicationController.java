package com.example.communicationlog.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.communicationlog.customexception.DataNotFoundException;
import com.example.communicationlog.dto.ActivityDto;
import com.example.communicationlog.dto.EnquiryDto;
import com.example.communicationlog.model.CommunicationLog;
import com.example.communicationlog.service.CommunicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/communication-logs")
@Validated
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@GetMapping
	public ResponseEntity<List<CommunicationLog>> getAllCommunicationLogs() {
		List<CommunicationLog> communicationLogs = communicationService.getAllCommunicationLogs();
		return new ResponseEntity<>(communicationLogs, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getCommunicationLogById(@PathVariable(name = "communicationId") Integer id) {
		try {
			CommunicationLog communicationLog = communicationService.getCommunicationLogById(id);
			return new ResponseEntity<>(communicationLog, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found", HttpStatus.NOT_FOUND));
		}
	}
	
	@GetMapping("/user/{salesRepresentativeId}")	
	public ResponseEntity<?> getCommunicationLogBySalesRepresentativeId(@PathVariable(name="salesRepresentativeId") long salesRepresentativeId) {

	    Optional<CommunicationLog> communicationLog = communicationService.findCommunicationDataByUserId(salesRepresentativeId);
	    if (communicationLog.isPresent()) {
	        return new ResponseEntity<>(communicationLog.get(), HttpStatus.OK);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(createResponse("Communication log not found for sales representative", HttpStatus.NOT_FOUND));
	    }
	}
	
	@GetMapping("/sales-representative")
    public ResponseEntity<?> getCommunicationLogsBySalesRepresentativeId() {
       
        List<CommunicationLog> communicationLogs = communicationService.findCommunicationLogsBySalesRepresentativeId();
        if (communicationLogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Communication logs not found for sales representative", HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(communicationLogs, HttpStatus.OK);
        }
    }
	@GetMapping("/enquiryId")
    public ResponseEntity<Integer> getEnquiryId() {
        try {
            Integer enquiryId = this.communicationService.findEnquiryId();
            if (enquiryId != null) {
                return ResponseEntity.ok(enquiryId);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception (you can use a logging framework like SLF4J)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



	@PostMapping
	public ResponseEntity<?> createCommunicationLog(@Valid @RequestBody CommunicationLog communicationLog) {
		try {
			CommunicationLog createdCommunicationLog = communicationService.createCommunicationLog(communicationLog);
			return new ResponseEntity<>(createResponse("Communication log created successfully", HttpStatus.CREATED,
					createdCommunicationLog), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(createResponse("Failed to create communication log", HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}


	@PutMapping("{id}")
	public ResponseEntity<?> updateCommunicationLog(@PathVariable(name = "communicationId") Integer id,
			@Valid @RequestBody CommunicationLog communicationLog) {
		try {
			CommunicationLog updatedCommunicationLog = communicationService.updateCommunicationLog(id,
					communicationLog);
			return new ResponseEntity<>(
					createResponse("Communication log updated successfully", HttpStatus.OK, updatedCommunicationLog),
					HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found for update", HttpStatus.NOT_FOUND));
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCommunicationLog(@PathVariable(name = "communicationId") Integer id) {
		try {
			communicationService.deleteCommunicationLog(id);
			return new ResponseEntity<>(createResponse("Communication log deleted successfully", HttpStatus.OK),
					HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found for delete", HttpStatus.NOT_FOUND));
		}
	}
	
	
	@PostMapping("enquiry")
    public ResponseEntity<?> createCommLogWithEnquiry(@RequestBody EnquiryDto enqObj) {
        try {
            CommunicationLog communicationLog = communicationService.createCommLogWithEnquiry(enqObj);
            return new ResponseEntity<>(createResponse("Communication log created successfully", HttpStatus.CREATED, communicationLog), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create communication log", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("activity")
    public ResponseEntity<?> createCommunicationLogWithActivity(@RequestBody ActivityDto actObj) {
        try {
            CommunicationLog communicationLog = communicationService.createCommunicationLogWithActivity(actObj);
            return new ResponseEntity<>(createResponse("Communication log created successfully", HttpStatus.CREATED, communicationLog), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create communication log", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }


	private Map<String, Object> createResponse(String message, HttpStatus status) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", status.value());
		response.put("message", message);
		return response;
	}

	private Map<String, Object> createResponse(String message, HttpStatus status, Object data) {
		Map<String, Object> response = createResponse(message, status);
		response.put("data", data);
		return response;
	}
}
