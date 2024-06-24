package com.example.ActivityManagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActivityManagement.model.Activities;
import com.example.ActivityManagement.services.ActivitiesService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

	@Autowired
	ActivitiesService dataServices;
	
	 @GetMapping("/readActivities")
	    public ResponseEntity<List<Activities>> getAllActivities() {
		        List<Activities> courseTypes = dataServices.getAllActivities();
		        return new ResponseEntity<>(courseTypes, HttpStatus.OK);
	 	}

	    @GetMapping("/readActivityById/{id}")
	    public ResponseEntity<?> getCourseTypeById(@PathVariable Integer id) {
	        try {
	            Activities activityById= dataServices.getActivityById(id);
	            return new ResponseEntity<>(activityById, HttpStatus.OK);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse("Id not found", HttpStatus.NOT_FOUND));
	        }
	    }

	    @PostMapping("/createActivity")
	    	 public ResponseEntity<?> createCourseType(@Valid @RequestBody Activities activities) {
	    	        try {
	    	            Activities createActivity = dataServices.saveActivity(activities);
	    	            return new ResponseEntity<>(createResponse("Course type created successfully", HttpStatus.CREATED, createActivity), HttpStatus.CREATED);
	    	        } catch (Exception e) {
	    	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	    	                    .body(createResponse("Failed to create Activity", HttpStatus.INTERNAL_SERVER_ERROR));
	    	        }
	    	    }

	    @DeleteMapping("/deleteActivity/{id}")
	    public ResponseEntity<?> deleteCourseType(@PathVariable Integer id) {
	        try {
	            dataServices.deleteActivity(id);
	            return new ResponseEntity<>(createResponse("Course type deleted successfully", HttpStatus.OK), HttpStatus.OK);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(createResponse("Activity not found for delete", HttpStatus.NOT_FOUND));
	        }
	    }

	
	    @PutMapping("/updateActivity/{id}")
	    public ResponseEntity<?> updateCourseType(@PathVariable Integer id, @Valid @RequestBody Activities act) {
	        try {
	            Activities updatedActivities= dataServices.updateActivity(id, act);
	            return new ResponseEntity<>(createResponse("Course type updated successfully", HttpStatus.OK, updatedActivities), HttpStatus.OK);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(createResponse("Course type not found for update", HttpStatus.NOT_FOUND));
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
