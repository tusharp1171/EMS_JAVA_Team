package com.example.communicationlog.model;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class CommunicationLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer communicationId;
	private Integer enquiryId;
	private Integer customerId;
	private Integer actvityStatusId;
	private Integer salesRepresentativeId;
	private LocalDateTime communicationDate;
	private String communicationDetails;

}
