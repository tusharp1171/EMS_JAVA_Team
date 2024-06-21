package com.example.facultytimetable.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.facultytimetable.customexception.weekdays.DataNotFoundException;
import com.example.facultytimetable.model.TimeTable;
import com.example.facultytimetable.service.TimeTableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/timetable")
@Validated
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @GetMapping
    public ResponseEntity<List<TimeTable>> getAllTimeTables() {
        List<TimeTable> timeTables = timeTableService.getAllTimeTables();
        return new ResponseEntity<>(timeTables, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimeTableById(@PathVariable Integer id) {
        try {
            TimeTable timeTable = timeTableService.getTimeTableById(id);
            return new ResponseEntity<>(timeTable, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("TimeTable not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<?> createTimeTable(@Valid @RequestBody TimeTable timeTable) {
        try {
            TimeTable createdTimeTable = timeTableService.createTimeTable(timeTable);
            return new ResponseEntity<>(createResponse("TimeTable created successfully", HttpStatus.CREATED, createdTimeTable), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create TimeTable", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTimeTable(@PathVariable Integer id, @Valid @RequestBody TimeTable timeTable) {
        try {
            TimeTable updatedTimeTable = timeTableService.updateTimeTable(id, timeTable);
            return new ResponseEntity<>(createResponse("TimeTable updated successfully", HttpStatus.OK, updatedTimeTable), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("TimeTable not found for update", HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTimeTable(@PathVariable Integer id) {
        try {
            timeTableService.deleteTimeTable(id);
            return new ResponseEntity<>(createResponse("TimeTable deleted successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("TimeTable not found for delete", HttpStatus.NOT_FOUND));
        }
    }

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
