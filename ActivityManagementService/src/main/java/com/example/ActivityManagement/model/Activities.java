package com.example.ActivityManagement.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table
public class Activities {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "dueDate", nullable = false)
	private LocalDateTime dueDate;
	
	@Column(name = "salesRepresentativeId")
	private int salesRepresentativeId;
	
	@Column(name = "summary")
	@NotBlank(message = "Summary is mandatory")
	private String summary;
	
	
	@NotNull(message = "Type cannot be null")
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ActivityTypeId", referencedColumnName = "id")
    private ActivityType activityType;

	@NotNull(message = "Staus cannot be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ActivityStatusId", referencedColumnName = "id")
    private ActivityStatus activityStatus;

	

	
}
