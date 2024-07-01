package com.example.enrollmentpipeline.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.repository.support.Repositories;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.enrollmentpipeline.customexception.EntityNotFoundException;
import com.example.enrollmentpipeline.customexception.ErrorDetails;
import com.example.enrollmentpipeline.dto.AdmissionsDTO;
import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.model.Enquiries;
import com.example.enrollmentpipeline.model.PipeLinePhases;
import com.example.enrollmentpipeline.repository.CoursesRepository;
import com.example.enrollmentpipeline.repository.EnquiriesRepository;
import com.example.enrollmentpipeline.service.EnquiriesService;
import com.example.enrollmentpipeline.serviceimpl.CoursesServiceImpl;

import io.swagger.models.HttpMethod;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("enquiries")
public class EnquiriesController {
	@Autowired
	private EnquiriesService enquiriesService;
	@Autowired
	private EnquiriesRepository enquiriesRepository;
	@Autowired
	private CoursesRepository coursesRepository;

	private AdmissionsDTO admissionsDTO;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/add/{cid}/{pid}")
	public ResponseEntity<?> createEnquiry(@RequestBody Enquiries enquiry, @PathVariable("cid") int id,
			@PathVariable("pid") int pipeLinePhaseId, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				String fieldName = error.getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			}
			ErrorDetails errorDetails = new ErrorDetails("Validation Failed", LocalDateTime.now(), errors,
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(errorDetails);
		}

		Enquiries savedEnquiry = enquiriesService.saveEnquiry(enquiry, id, pipeLinePhaseId);
		// long response =
		// restTemplate.getForObject("http://192.168.1.113:8080/api/test/tokenusername",
		// long.class);

	restTemplate.postForObject("http://192.168.1.126:8084/api/communication-logs/enquiry", savedEnquiry,
				Enquiries.class);

		return ResponseEntity.ok(savedEnquiry);
	}

	@GetMapping("/{id}")
	public Enquiries getEnquiryById(@PathVariable int id) {
		return enquiriesService.getEnquiryById(id);
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

	@GetMapping("/getAllEnquiriesNotAdmitted")
	public ResponseEntity<List<Enquiries>> checkAdmissionDoneOrNot() {
		List<AdmissionsDTO> responseList;
		try {
			// Fetch admissions data from external API
			ResponseEntity<List<AdmissionsDTO>> responseEntity = restTemplate.exchange(
					"http://192.168.1.121:8086/api/admissions", org.springframework.http.HttpMethod.GET, null,
					new ParameterizedTypeReference<List<AdmissionsDTO>>() {
					});

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				responseList = responseEntity.getBody();
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		List<Integer> notAdmittedEnquiryIds = new ArrayList<>();

		// Iterate through each enquiry id in responseList
		for (AdmissionsDTO admissionsDTO : responseList) {
			int enquiryId = admissionsDTO.getEnquiryId();

			// Check if enquiriesService has an Enquiries with this enquiryId
			Optional<Enquiries> optionalEnquiry = Optional.ofNullable(enquiriesService.getEnquiryById(enquiryId));
			if (optionalEnquiry.isEmpty()) {
				// Enquiry not found, add enquiryId to notAdmittedEnquiryIds
				notAdmittedEnquiryIds.add(enquiryId);
			}
		}

		List<Enquiries> notAdmittedEnquiries = new ArrayList<>();

		// Retrieve Enquiries objects for each enquiryId in notAdmittedEnquiryIds
		for (Integer enquiryId : notAdmittedEnquiryIds) {
			Optional<Enquiries> optionalEnquiry = Optional.ofNullable(enquiriesService.getEnquiryById(enquiryId));
			optionalEnquiry.ifPresent(notAdmittedEnquiries::add);
		}

		return ResponseEntity.ok(notAdmittedEnquiries);
	}

	@GetMapping("/getAllEnquiriesOfToday")
	public ResponseEntity<List<Enquiries>> getAllEnquiriesOfToday() {
		LocalDateTime today = LocalDateTime.now();

		List<Enquiries> enquiriesOfToday = enquiriesService.getEnquiriesByDate(today);

		if (enquiriesOfToday.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(enquiriesOfToday);
		}
	}
	
	
	@GetMapping("/getAllEnquiriesOfTodayByCourseId/{courseId}")
	public ResponseEntity<List<Enquiries>> getAllEnquiriesOfTodayByCourseId(@PathVariable int courseId) {
	    LocalDateTime today = LocalDateTime.now();
	    
	    List<Enquiries> enquiriesOfToday = enquiriesService.getEnquiriesByDate(today);

	    List<Enquiries> enquiriesByCourseId = new ArrayList<>();
	    for (Enquiries enquiry : enquiriesOfToday) {
	        if (enquiry.getCourses().getId() == courseId) { // Assuming Enquiries has a Course reference
	            enquiriesByCourseId.add(enquiry);
	        }
	    }

	    if (enquiriesByCourseId.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(enquiriesByCourseId);
	    }
	}
	
	@GetMapping("/getEnquiriesBetweenDates/{startDate}/{endDate}")
	public ResponseEntity<List<Enquiries>> getEnquiriesBetweenDates(
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

	    if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
	        return ResponseEntity.badRequest().build();
	    }

	    LocalDateTime startDateTime = startDate.atStartOfDay();
	    LocalDateTime endDateTime = endDate.atTime(23, 59, 59); // Set end time to end of the day

	    List<Enquiries> enquiriesBetweenDates = enquiriesService.getEnquiriesBetweenDates(startDateTime, endDateTime);

	    if (enquiriesBetweenDates.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(enquiriesBetweenDates);
	    }
	}

	
	@GetMapping("/getEnquiriesBetweenDatesByCourseId/{startDate}/{endDate}/{courseId}")
	public ResponseEntity<List<Enquiries>> getEnquiriesBetweenDatesByCourseId(
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @PathVariable int courseId) {

	    if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
	        return ResponseEntity.badRequest().build();
	    }

	    LocalDateTime startDateTime = startDate.atStartOfDay();
	    LocalDateTime endDateTime = endDate.atTime(23, 59, 59); // Set end time to end of the day

	    List<Enquiries> enquiriesBetweenDates = enquiriesService.getEnquiriesBetweenDates(startDateTime, endDateTime);

	    List<Enquiries> enquiriesBetweenDatesByCourseId = new ArrayList<>();
	    for (Enquiries enquiry : enquiriesBetweenDates) {
	        if (enquiry.getCourses().getId() == courseId) { // Assuming Enquiries has a Course reference
	            enquiriesBetweenDatesByCourseId.add(enquiry);
	        }
	    }

	    if (enquiriesBetweenDatesByCourseId.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(enquiriesBetweenDatesByCourseId);
	    }
	}


//	@GetMapping("/getEnquiriesBetweenDates/{startDate}/{endDate}")
//	public ResponseEntity<List<Enquiries>> getEnquiriesBetweenDates(
//	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
//
//	    if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
//	        return ResponseEntity.badRequest().build();
//	    }
//
//	    List<Enquiries> enquiriesBetweenDates = enquiriesService.getEnquiriesBetweenDates(startDate, endDate);
//
//	    if (enquiriesBetweenDates.isEmpty()) {
//	        return ResponseEntity.noContent().build();
//	    } else {
//	        return ResponseEntity.ok(enquiriesBetweenDates);
//	    }
//	}
//	
//
//	@GetMapping("/getEnquiriesBetweenDates/{startDate}/{endDate}/{courseId}")
//	public ResponseEntity<List<Enquiries>> getEnquiriesBetweenDatesByCourseId(
//	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
//	        @PathVariable int courseId) {
//
//	    if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
//	        return ResponseEntity.badRequest().build();
//	    }
//
//	    List<Enquiries> enquiriesBetweenDates = enquiriesService.getEnquiriesBetweenDates(startDate, endDate);
//	    
//	    List<Enquiries> enquiriesBetweenDatesByCourseId = new ArrayList<>();
//	    for (Enquiries enquiry : enquiriesBetweenDates) {
//	        if (enquiry.getCourses().getId() == courseId) { // Assuming Enquiries has a Course reference
//	        	enquiriesBetweenDatesByCourseId.add(enquiry);
//	        }
//	    }
//	    
//	    if (enquiriesBetweenDatesByCourseId.isEmpty()) {
//	        return ResponseEntity.noContent().build();
//	    } else {
//	        return ResponseEntity.ok(enquiriesBetweenDatesByCourseId);
//	    }
//	}
	
	
//	@GetMapping("/getAllEnquiriesOfTodayByCourseId/{CourseId}")
//	public ResponseEntity<List<Enquiries>> getAllEnquiriesOfTodayByCourseId(int courseId) {
//		LocalDateTime today = LocalDateTime.now();
//		Courses c=coursesRepository.findById(courseId).orElseThrow();
//
//
//		List<Enquiries> enquiriesOfToday = enquiriesService.getEnquiriesByDate(today);
//		List<Enquiries> enquiriesByCourseId =enquiriesOfToday.contains(c). ;
//
//
//		List<Enquiries> commonElements = new ArrayList<>();
//
//		for (Enquiries e1 : enquiriesByCourseId) {
//			for (Enquiries e2 : enquiriesOfToday) {
//				if (e1.equals(e2)) {
//					commonElements.add(e1);
//					break; // Break inner loop since element found
//				}
//			}
//		}
//		if (commonElements.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		} else {
//			return ResponseEntity.ok(commonElements);
//		}
//	}

}