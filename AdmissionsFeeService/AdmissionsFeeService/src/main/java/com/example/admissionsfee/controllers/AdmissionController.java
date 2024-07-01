package com.example.admissionsfee.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admissionsfee.dto.AdmissionDTO;
import com.example.admissionsfee.entities.Admission;
import com.example.admissionsfee.entities.FeePayment;
import com.example.admissionsfee.service.AdmissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admissions")
@Tag(name = "Admissions", description = "Admissions management APIs")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @GetMapping
    @Operation(summary = "Get all admissions", description = "Retrieve a list of all admissions")
    public List<AdmissionDTO> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get admission by ID", description = "Retrieve a single admission by its ID")
    public ResponseEntity<AdmissionDTO> getAdmissionById(@PathVariable Long id) {
        AdmissionDTO admissionDTO = admissionService.getAdmissionById(id);
        return ResponseEntity.ok(admissionDTO);
    }

    @PostMapping
    public ResponseEntity<Admission> createAdmission(@RequestBody Admission admissionDTO) {
    	
        Admission createdAdmission = admissionService.createAdmission(admissionDTO);
        return ResponseEntity.ok(createdAdmission);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing admission", description = "Update an existing admission by its ID")
    public ResponseEntity<AdmissionDTO> updateAdmission(@PathVariable Long id, @RequestBody AdmissionDTO admissionDTO) {
        AdmissionDTO updatedAdmission = admissionService.updateAdmission(id, admissionDTO);
        return ResponseEntity.ok(updatedAdmission);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an admission", description = "Delete an admission by its ID")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/byDate")
    public ResponseEntity<List<Admission>> getAdmissionsByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Admission> admissions = admissionService.getAdmissionsByDate(date);
        return ResponseEntity.ok(admissions);
    }
    @GetMapping("/today")
    public ResponseEntity<List<Admission>> getAdmissionsByToday() {
        Date today = new Date();
        List<Admission> admissions = admissionService.getAdmissionsByDate(today);
        return ResponseEntity.ok(admissions);
    }
    @GetMapping("/byDateRange")
    public ResponseEntity<List<Admission>> getAdmissionsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Admission> admissions = admissionService.getAdmissionsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(admissions);
    }	
    
    @GetMapping("/todaycollectammount")
    public double todayamount()
    {
    	double sum=0;
    	 Date today = new Date();
         List<Admission> admissions = admissionService.getAdmissionsByDate(today);
      
        for(Admission ad: admissions)
        {
        	List<FeePayment> fees = ad.getFeePayments();
        	for(FeePayment fee:fees) {
        		sum=sum+fee.getAmountCredited();
        	}
        }
    	
    	return sum;
    	
    }
    
    
}
