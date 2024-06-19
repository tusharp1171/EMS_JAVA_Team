package com.example.coursemanagement.controller;
import com.example.coursemanagement.model.CourseType;
import com.example.coursemanagement.service.CourseTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/coursetypes")
@Validated
public class CourseTypeController {

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping
    public ResponseEntity<List<CourseType>> getAllCourseTypes() {
        List<CourseType> courseTypes = courseTypeService.getAllCourseTypes();
        return new ResponseEntity<>(courseTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseTypeById(@PathVariable Integer id) {
        try {
            CourseType courseType = courseTypeService.getCourseTypeById(id);
            return new ResponseEntity<>(courseType, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Course type not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCourseType(@Valid @RequestBody CourseType courseType) {
        try {
            CourseType createdCourseType = courseTypeService.createCourseType(courseType);
            return new ResponseEntity<>(createResponse("Course type created successfully", HttpStatus.CREATED, createdCourseType), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create course type", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseType(@PathVariable Integer id, @Valid @RequestBody CourseType courseType) {
        try {
            CourseType updatedCourseType = courseTypeService.updateCourseType(id, courseType);
            return new ResponseEntity<>(createResponse("Course type updated successfully", HttpStatus.OK, updatedCourseType), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Course type not found for update", HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseType(@PathVariable Integer id) {
        try {
            courseTypeService.deleteCourseType(id);
            return new ResponseEntity<>(createResponse("Course type deleted successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Course type not found for delete", HttpStatus.NOT_FOUND));
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