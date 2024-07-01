package com.example.admissionsfee.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor	
public class Admission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private int courseTypeId;
    private int subjectId;
    private long userid;
    private int enquiryId;


    private Date admissionDate;

    private String description;

    private String status;
    
    @OneToMany(mappedBy = "admission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeePayment> feePayments;
}