package com.example.admissionsfee.controllers;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.admissionsfee.dto.AdmissionDTO;
import com.example.admissionsfee.dto.FeePaymentDTO;
import com.example.admissionsfee.dto.SyllabusDTO;
import com.example.admissionsfee.entities.Admission;
import com.example.admissionsfee.entities.FeePayment;
import com.example.admissionsfee.repo.AdmissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/communication")
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired 
    private AdmissionRepository admissionRepository;

    
    
    @PostMapping("/post")
    public ResponseEntity<Admission> postAdmission(
    		@RequestParam Integer courseTypeId,
          @RequestParam Integer subjectId,
          @RequestParam String sectionName,
          @RequestParam String topicName,
    		@RequestBody String entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AdmissionDTO admissionDTO = objectMapper.readValue(entity, AdmissionDTO.class); 
            
            
            Integer enquiryId = restTemplate.getForObject(
                    "http://192.168.1.131:8084/api/communication-logs/enquiryId", Integer.class);
            System.out.println(enquiryId);
            
            ResponseEntity<Long> userIdResponse = restTemplate.getForEntity(
                "http://192.168.1.135:8080/api/test/tokenusername", Long.class);
        Long userId = userIdResponse.getBody();
        System.out.println(userId);           

            Admission admission = new Admission();
            
            admission.setCourseTypeId(courseTypeId);
            admission.setSubjectId(subjectId);
            admission.setEnquiryId(enquiryId);
            admission.setUserid(userId);
            admission.setAdmissionDate(admissionDTO.getAdmissionDate());
            admission.setDescription(admissionDTO.getDescription());
            admission.setStatus(admissionDTO.getStatus());

            // Initialize the feePayments list
            List<FeePayment> feePayments = new ArrayList<>();

            // Handle FeePayments
            for (FeePaymentDTO feePaymentDTO : admissionDTO.getFeePayments()) {
                FeePayment feePayment = new FeePayment();
                feePayment.setAmountCredited(feePaymentDTO.getAmountCredited());
                feePayment.setBalanceAmount(feePaymentDTO.getBalanceAmount());
                feePayment.setPaymentDate(feePaymentDTO.getPaymentDate());
                feePayment.setPaymentMethod(feePaymentDTO.getPaymentMethod());
                feePayment.setNextDueDate(feePaymentDTO.getNextDueDate());
                
                feePayment.setAdmission(admission);
                feePayments.add(feePayment);
            }
            admission.setFeePayments(feePayments);
            
            
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
          formData.add("subjectId", subjectId.toString());
          formData.add("courseTypeId", courseTypeId.toString());
          formData.add("sectionName", sectionName);
          formData.add("topicName", topicName);

          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

          HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

          // Retrieve the syllabus details
            restTemplate.exchange(
                  "http://192.168.1.135:8085/api/syllabuses", HttpMethod.POST, requestEntity, SyllabusDTO.class);

            

            Admission savedAdmission = admissionRepository.save(admission); 

            return new ResponseEntity<>(savedAdmission, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/syllbusid")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/today")
    public ResponseEntity<List<Admission>> getAdmissionsToday() {
        // Get today's date at midnight
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        // Get end of today's date
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endOfDay = calendar.getTime();

        // Find admissions by today's date range
        List<Admission> admissions = admissionRepository.findByAdmissionDateBetween(startOfDay, endOfDay);

        // Return the response
        return ResponseEntity.ok(admissions);
    }
    
}