package com.example.ActivityManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActivityManagement.model.ActivityStatus;
import com.example.ActivityManagement.services.ActivityStatusService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/activity-statuses")
public class ActivityStatusController {

    @Autowired
    private ActivityStatusService activityStatusService;

    @GetMapping("/readAllStatus")
    public ResponseEntity<List<ActivityStatus>> getAllActivityStatuses() {
        return ResponseEntity.ok(activityStatusService.getAllActivityStatuses());
    }

    @GetMapping("/readStatusById/{id}")
    public ResponseEntity<ActivityStatus> getActivityStatusById(@PathVariable Integer id) {
        ActivityStatus activityStatus = activityStatusService.getActivityStatusById(id);
        if (activityStatus != null) {
            return ResponseEntity.ok(activityStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createActivityStatus")
    public ResponseEntity<ActivityStatus> createActivityStatus(@RequestBody ActivityStatus activityStatus) {
        return ResponseEntity.ok(activityStatusService.saveActivityStatus(activityStatus));
    }

    @DeleteMapping("/deleteActivityStatus/{id}")
    public ResponseEntity<Void> deleteActivityStatus(@PathVariable Integer id) {
        activityStatusService.deleteActivityStatus(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/updateActivityStatus/{id}")
    public ResponseEntity<ActivityStatus> putMethodName(@PathVariable int id, @RequestBody ActivityStatus actStat) {
        ActivityStatus updateStat = this.activityStatusService.updateActivityStatus(id, actStat);
        return ResponseEntity.ok().body(updateStat);
    }
}
