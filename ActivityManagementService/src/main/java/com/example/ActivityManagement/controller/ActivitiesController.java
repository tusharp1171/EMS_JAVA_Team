package com.example.ActivityManagement.controller;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.openssl.openssl_h;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActivityManagement.dto.ActivitiesDto;
import com.example.ActivityManagement.model.Activities;
import com.example.ActivityManagement.repository.ActivitiesRepo;
import com.example.ActivityManagement.services.ActivitiesService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

	@Autowired
	ActivitiesService dataServices;
	
	 @GetMapping("/readActivities")
	    public ResponseEntity<List<Activities>> getAllActivities() {
	        return ResponseEntity.ok(dataServices.getAllActivities());
	    }

	    @GetMapping("/readActivityById/{id}")
	    public ResponseEntity<Activities> getActivityById(@PathVariable Integer id) {
	        Activities activity = dataServices.getActivityById(id);
	        if (activity != null) {
	            return ResponseEntity.ok(activity);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping("/createActivity")
	    public ResponseEntity<Activities> createActivity(@RequestBody Activities activity) {
	        return ResponseEntity.ok(dataServices.saveActivity(activity));
	    }

	    @DeleteMapping("/deleteActivity/{id}")
	    public ResponseEntity<Activities> deleteActivity(@PathVariable Integer id) {
	        dataServices.deleteActivity(id);
	        return ResponseEntity.noContent().build();
	    }

	
	    @PutMapping("/updateActivity/{id}")
	    public ResponseEntity<Activities> updateActivity(@PathVariable int id, @RequestBody Activities act) {
	  
	    	Activities updateAct = this.dataServices.updateActivity(id, act);
	    	return ResponseEntity.ok().body(updateAct);
	    }
}
