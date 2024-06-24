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
import com.example.ActivityManagement.model.ActivityType;
import com.example.ActivityManagement.services.ActivityTypeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/activity-types")
@Validated
public class ActivityTypeController {

    @Autowired
    private ActivityTypeService activityTypeService;

    @GetMapping("/readActivityType")
    public ResponseEntity<List<ActivityType>> getAllActivityTypes() {
        return ResponseEntity.ok(activityTypeService.getAllActivityTypes());
    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<?> getActivityTypeById(@PathVariable Integer id) {
        
    	try {
    		ActivityType activityType = activityTypeService.getActivityTypeById(id);
    		return new ResponseEntity<>(activityType, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Status Not Found", HttpStatus.NOT_FOUND));
		}
        
    }

    	
    @PostMapping("/createActivityType")
    public ResponseEntity<?> createActivityType(@Valid @RequestBody ActivityType typeName) {
    	try {
        	ActivityType createType = activityTypeService.saveActivityType(typeName);
        	return new ResponseEntity<>(createResponse("Status Created Successfully!", HttpStatus.CREATED, createType), HttpStatus.CREATED);
		} finally {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse("Failed to creare Status! ", HttpStatus.INTERNAL_SERVER_ERROR));
		}
    }
    

    @DeleteMapping("/deleteActivityType/{id}")
    public ResponseEntity<?> deleteActivityType(@PathVariable Integer id) {
//        activityTypeService.deleteActivityType(id);
//        return ResponseEntity.noContent().build();
    	try {
			activityTypeService.deleteActivityType(id);
			return new ResponseEntity<>(createResponse("Deleted Successfully", HttpStatus.OK), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Id Not Found!", HttpStatus.NOT_FOUND));
		}
    }
    
    @PutMapping("/updateActivityType/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody ActivityType activityType) {
        
    	try {
    		ActivityType updateType = this.activityTypeService.updateActivityType(id, activityType);
    		return new ResponseEntity<>(createResponse("Updated Succssfully!", HttpStatus.OK, updateType), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Status Not Found!", HttpStatus.NOT_FOUND));
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
