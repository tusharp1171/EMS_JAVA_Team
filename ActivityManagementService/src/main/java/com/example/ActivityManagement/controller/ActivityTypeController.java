package com.example.ActivityManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActivityManagement.model.ActivityType;
import com.example.ActivityManagement.services.ActivityTypeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/activity-types")
public class ActivityTypeController {

    @Autowired
    private ActivityTypeService activityTypeService;

    @GetMapping("/readActivityType")
    public ResponseEntity<List<ActivityType>> getAllActivityTypes() {
        return ResponseEntity.ok(activityTypeService.getAllActivityTypes());
    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<ActivityType> getActivityTypeById(@PathVariable Integer id) {
        ActivityType activityType = activityTypeService.getActivityTypeById(id);
        if (activityType != null) {
            return ResponseEntity.ok(activityType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createActivityType")
    public ResponseEntity<ActivityType> createActivityType(@RequestBody ActivityType activityType) {
        return ResponseEntity.ok(activityTypeService.saveActivityType(activityType));
    }

    @DeleteMapping("/deleteActivityType/{id}")
    public ResponseEntity<Void> deleteActivityType(@PathVariable Integer id) {
        activityTypeService.deleteActivityType(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/updateActivityType/{id}")
    public ResponseEntity<ActivityType> putMethodName(@PathVariable int id, @RequestBody ActivityType activityType) {
                
    	ActivityType updateType = this.activityTypeService.updateActivityType(id, activityType);
        return ResponseEntity.ok().body(updateType);
    }
}
