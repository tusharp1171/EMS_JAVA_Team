package com.example.enrollmentpipeline.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.enrollmentpipeline.customexception.EntityNotFoundException;
import com.example.enrollmentpipeline.customexception.ErrorDetails;
import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.model.Enquiries;
import com.example.enrollmentpipeline.model.PipeLinePhases;
import com.example.enrollmentpipeline.service.EnquiriesService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("enquiries")
public class EnquiriesController {
	@Autowired
	private EnquiriesService enquiriesService;
	
	
	private final RestTemplate restTemplate;

    @Autowired
    public EnquiriesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	 @PostMapping("/add/{cid}/{pid}")
	    public ResponseEntity<?> createEnquiry(@RequestBody Enquiries enquiry,
	                                           @PathVariable("cid") int id,
	                                           @PathVariable("pid") int pipeLinePhaseId,
	                                           BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            for (FieldError error : bindingResult.getFieldErrors()) {
	                String fieldName = error.getField();
	                String errorMessage = error.getDefaultMessage();
	                errors.put(fieldName, errorMessage);
	            }
	            ErrorDetails errorDetails = new ErrorDetails("Validation Failed", LocalDateTime.now(), errors);
	            return ResponseEntity.badRequest().body(errorDetails);
	        }

	        Enquiries savedEnquiry = enquiriesService.saveEnquiry(enquiry, id, pipeLinePhaseId);
	        return ResponseEntity.ok(savedEnquiry);
	    }
	
	@GetMapping("/{id}")
	public Enquiries getEnquiryById(@PathVariable int id) {
		        return enquiriesService.getEnquiryById(id) ;	
	}

	@GetMapping
	public List<Enquiries> getAllEnquiries() {
		return enquiriesService.getAllEnquiries();
	}

	@PutMapping("/{id}")
	public Enquiries updateEnquiry(@RequestBody @Valid Enquiries enquiry, @PathVariable int id) {
		return enquiriesService.updateEnquiry(enquiry, id);
	}

	@DeleteMapping("/{id}")
	public void deleteEnquiry(@PathVariable int id) {
		enquiriesService.deleteEnquiry(id);
	}
	

    @GetMapping("/user")
    public int getUserId() {
         String response = restTemplate.getForObject("https://localhost:8081/Users", String.class);
         int userId=Integer.parseInt(response);
         return userId;
        
    }
}
