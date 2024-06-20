package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Subject;
import com.example.coursemanagement.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectsController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable Integer id) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Subject not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@Valid @RequestBody Subject subject) {
        try {
            Subject createdSubject = subjectService.createSubject(subject);
            return new ResponseEntity<>(createResponse("Subject created successfully", HttpStatus.CREATED, createdSubject), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createResponse("Failed to create subject", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Integer id, @Valid @RequestBody Subject subject) {
        try {
            Subject updatedSubject = subjectService.updateSubject(id, subject);
            return new ResponseEntity<>(createResponse("Subject updated successfully", HttpStatus.OK, updatedSubject), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Subject not found for update", HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
        try {
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(createResponse("Subject deleted successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Subject not found for delete", HttpStatus.NOT_FOUND));
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