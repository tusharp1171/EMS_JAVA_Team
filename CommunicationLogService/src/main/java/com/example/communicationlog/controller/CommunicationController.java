package com.example.communicationlog.controller;

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

import com.example.communicationlog.customexception.DataNotFoundException;
import com.example.communicationlog.model.CommunicationLog;
import com.example.communicationlog.service.CommunicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/communication-logs")
@Validated
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@GetMapping
	public ResponseEntity<List<CommunicationLog>> getAllCommunicationLogs() {
		List<CommunicationLog> communicationLogs = communicationService.getAllCommunicationLogs();
		return new ResponseEntity<>(communicationLogs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCommunicationLogById(@PathVariable(name = "communicationId") Integer id) {
		try {
			CommunicationLog communicationLog = communicationService.getCommunicationLogById(id);
			return new ResponseEntity<>(communicationLog, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found", HttpStatus.NOT_FOUND));
		}
	}

	@PostMapping
	public ResponseEntity<?> createCommunicationLog(@Valid @RequestBody CommunicationLog communicationLog) {
		try {
			CommunicationLog createdCommunicationLog = communicationService.createCommunicationLog(communicationLog);
			return new ResponseEntity<>(createResponse("Communication log created successfully", HttpStatus.CREATED,
					createdCommunicationLog), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(createResponse("Failed to create communication log", HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}

	@PostMapping("{userId}")
	public ResponseEntity<CommunicationLog> createCommunicationLog(@PathVariable Integer userId) {
		try {
			CommunicationLog communicationData = this.communicationService.getAllCommunicationData(userId);
			return ResponseEntity.ok().body(communicationData);
			
		} 
		catch (Exception ex) {

		}
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCommunicationLog(@PathVariable(name = "communicationId") Integer id,
			@Valid @RequestBody CommunicationLog communicationLog) {
		try {
			CommunicationLog updatedCommunicationLog = communicationService.updateCommunicationLog(id,
					communicationLog);
			return new ResponseEntity<>(
					createResponse("Communication log updated successfully", HttpStatus.OK, updatedCommunicationLog),
					HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found for update", HttpStatus.NOT_FOUND));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCommunicationLog(@PathVariable(name = "communicationId") Integer id) {
		try {
			communicationService.deleteCommunicationLog(id);
			return new ResponseEntity<>(createResponse("Communication log deleted successfully", HttpStatus.OK),
					HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(createResponse("Communication log not found for delete", HttpStatus.NOT_FOUND));
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
