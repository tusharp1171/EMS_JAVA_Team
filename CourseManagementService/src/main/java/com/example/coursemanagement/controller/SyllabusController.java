package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.SyllabusDTO;
import com.example.coursemanagement.model.Syllabus;
import com.example.coursemanagement.service.SyllabusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/syllabuses")
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;

	    @GetMapping
	    public ResponseEntity<List<Syllabus>> getAllSyllabuses() {
	        List<Syllabus> syllabuses = syllabusService.getAllSyllabi();
	        return new ResponseEntity<>(syllabuses, HttpStatus.OK);
	    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Integer id) {
    	try {
            Syllabus syllabus = syllabusService.getSyllabusById(id);
            return new ResponseEntity<>(syllabus, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Syllabus not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<Syllabus> createSyllabus(@RequestParam Integer subjectId,
                                                   @RequestParam Integer courseTypeId,
                                                   @RequestParam String sectionName,
                                                   @RequestParam String topicName) {
        Syllabus createdSyllabus = syllabusService.createSyllabus(subjectId, courseTypeId, sectionName, topicName);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSyllabus);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSyllabus(@PathVariable Integer id, @Valid @RequestBody Syllabus syllabus) {
        try {
            Syllabus updatedSyllabus = syllabusService.updateSyllabus(id, syllabus);
            return new ResponseEntity<>(createResponse("Syllabus updated successfully", HttpStatus.OK, updatedSyllabus), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Syllabus not found for update", HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSyllabus(@PathVariable Integer id) {
        try {
            syllabusService.deleteSyllabus(id);
            return new ResponseEntity<>(createResponse("Syllabus deleted successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("Syllabus not found for delete", HttpStatus.NOT_FOUND));
        }
    }
    @GetMapping("/courseType/{courseTypeId}/syllabi")
    public List<SyllabusDTO> getSyllabiByCourseType(@PathVariable Integer courseTypeId) {
        return syllabusService.getSyllabiByCourseType(courseTypeId).stream()
                .map(SyllabusDTO::new)
                .collect(Collectors.toList());
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