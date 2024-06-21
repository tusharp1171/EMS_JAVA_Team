package com.example.ActivityManagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActivityManagement.model.ActivityStatus;
import com.example.ActivityManagement.services.ActivityStatusService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/activity-statuses")
@Validated
public class ActivityStatusController {

    @Autowired
    private ActivityStatusService activityStatusService;

    @GetMapping("/readAllStatus")
    public ResponseEntity<List<ActivityStatus>> getAllActivityStatuses() {
    	List<ActivityStatus> activityStat = activityStatusService.getAllActivityStatuses();
        return new ResponseEntity<>(activityStat, HttpStatus.OK);
    }

    @GetMapping("/readStatusById/{id}")
    public ResponseEntity<?> getActivityStatusById(@PathVariable Integer id) {
    	try {
    		ActivityStatus activityStatus = activityStatusService.getActivityStatusById(id);
    		return new ResponseEntity<>(activityStatus, HttpStatus.OK);
    	}
    	catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Status Not Found  ", HttpStatus.NOT_FOUND));
		}
    }

    @PostMapping("/createActivityStatus")
    public ResponseEntity<?> createActivityStatus(@Valid @RequestBody ActivityStatus actStatus) {
//        return ResponseEntity.ok(activityStatusService.saveActivityStatus(activityStatus));
    	try {
        	ActivityStatus createStatus = activityStatusService.saveActivityStatus(actStatus);
        	return new ResponseEntity<>(createResponse("Status Created Successfully!", HttpStatus.CREATED, createStatus), HttpStatus.CREATED);
		} finally {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse("Failed to creare Status! ", HttpStatus.INTERNAL_SERVER_ERROR));
		}
    }

    @DeleteMapping("/deleteActivityStatus/{id}")
    public ResponseEntity<?> deleteActivityStatus(@PathVariable Integer id) {
//        activityStatusService.deleteActivityStatus(id);
//        return ResponseEntity.noContent().build();
    	try {
    		activityStatusService.deleteActivityStatus(id);
    		return new ResponseEntity<>(createResponse("Deleted Successfully", HttpStatus.OK),HttpStatus.OK);
		} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Id Not Found", HttpStatus.NOT_FOUND));			
		}
    }
    
    @PutMapping("/updateActivityStatus/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable int id,@Valid @RequestBody ActivityStatus actStat) {
//        ActivityStatus updateStat = this.activityStatusService.updateActivityStatus(id, actStat);
//        return ResponseEntity.ok().body(updateStat);
    	try {
    		ActivityStatus updateStatus = activityStatusService.updateActivityStatus(id, actStat);
    		return new ResponseEntity<>(createResponse("Status Updated Successfully", HttpStatus.OK, updateStatus), HttpStatus.OK);
    	}catch (Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Status Not Found", HttpStatus.NOT_FOUND));
		}
    	
    	
    }
    
 // Helper method to create response map with timestamp and status
    private Map<String, Object> createResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("message", message);
        return response;
    }

    // Helper method to include data in the response
    private Map<String, Object> createResponse(String message, HttpStatus status, Object data) {
        Map<String, Object> response = createResponse(message, status);
        response.put("data", data);
        return response;
    }
}
