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
import com.example.facultytimetable.model.WeekDays;
import com.example.facultytimetable.service.WeekService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/week-days")
@Validated
public class WeeksController {

    @Autowired
    private WeekService weekService;

    @GetMapping
    public ResponseEntity<List<WeekDays>> getAllWeekDays() {
        List<WeekDays> weekDays = weekService.getAllWeekDays();
        return new ResponseEntity<>(weekDays, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWeekDayById(@PathVariable Integer id) {
        try {
            WeekDays weekDay = weekService.getWeekDayById(id);
            return new ResponseEntity<>(weekDay, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("WeekDay not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<?> createWeekDay(@Valid @RequestBody WeekDays weekDay) {
        try {
            WeekDays createdWeekDay = weekService.createWeekDay(weekDay);
            return new ResponseEntity<>(createResponse("WeekDay created successfully", HttpStatus.CREATED, createdWeekDay), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create WeekDay", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWeekDay(@PathVariable Integer id, @Valid @RequestBody WeekDays weekDay) {
        try {
            WeekDays updatedWeekDay = weekService.updateWeekDay(id, weekDay);
            return new ResponseEntity<>(createResponse("WeekDay updated successfully", HttpStatus.OK, updatedWeekDay), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("WeekDay not found for update", HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWeekDay(@PathVariable Integer id) {
        try {
            weekService.deleteWeekDay(id);
            return new ResponseEntity<>(createResponse("WeekDay deleted successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("WeekDay not found for delete", HttpStatus.NOT_FOUND));
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
