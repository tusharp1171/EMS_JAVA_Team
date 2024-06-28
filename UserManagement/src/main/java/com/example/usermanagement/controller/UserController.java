package com.example.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.usermanagement.dto.Activitydto;
import com.example.usermanagement.dto.CouseEnquriymapDTO;
import com.example.usermanagement.dto.EnquiryDto;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.model.Users;
import com.example.usermanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired

	UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/add")
	public ResponseEntity<Users> addUsers(@Valid @RequestBody Users users) {
		Users users2 = userService.addUsers(users);
		return new ResponseEntity<Users>(users2, HttpStatus.CREATED);

	}

	@PostMapping("/Enquiry/{cid}/{pid}")
	public String addCourseEnquiryMapDTO(@RequestBody CouseEnquriymapDTO courseEnquiryMapDTO,
			@PathVariable("cid") int cid, @PathVariable("pid") int pid) {
		try {
			// Get the user ID from an external service
			ResponseEntity<Long> userIdResponse = restTemplate
					.getForEntity("http://192.168.1.135" + ":8080/api/test/tokenusername", Long.class);
			Long userId = userIdResponse.getBody();
			if (userId == null) {
				throw new ResourceNotFoundException("User ID not found");
			}

			// Update EnquiryDto and Activitydto with the user ID
			EnquiryDto enquiryDto = courseEnquiryMapDTO.getEnquiryDto();
			enquiryDto.setSalesPersonId(userId);
			Activitydto activityDto = courseEnquiryMapDTO.getActivitydto();
			activityDto.setSalesRepresentativeId(userId);

			// Get pipeline phase ID and course type ID from their respective services
			// Uncomment and use if needed
			// ResponseEntity<Integer> pipelineIdResponse =
			// restTemplate.getForEntity("http://192.168.1:8083/phases/{pid}/",
			// Integer.class);
			// int pipelineId = pipelineIdResponse.getBody();
			//
			// ResponseEntity<Integer> courseTypeIdResponse =
			// restTemplate.getForEntity("http://192.168.1:8083/Courses/{cid}",
			// Integer.class);
			// int courseTypeId = courseTypeIdResponse.getBody();
			// enquiryDto.setCourseId(courseTypeIdResponse.getBody());
			// Check if the enquiryDto's courseId matches pipelineId
			// if (enquiryDto.getCourseId() == courseTypeId &&
			// enquiryDto.getPipeLinePhaseId() == pipelineId) {

			// Send the EnquiryDto to the external service
			restTemplate.postForEntity("http://192.168.1.124:8083/enquiries/add/" + pid + "/" + cid, enquiryDto,
					EnquiryDto.class);
			// Send the ActivityDto to the external service
			restTemplate.postForEntity("http://192.168.1.134:8082/api/activities/createActivity", activityDto,
					Activitydto.class);

			return "Enquiry and activity added successfully";
		} catch (ResourceNotFoundException ex) {
			throw new ResourceNotFoundException("Resource not found: " + ex.getMessage());
		} catch (Exception ex) {
			throw new RuntimeException("Error occurred: " + ex.getMessage());
		}
	}

}
