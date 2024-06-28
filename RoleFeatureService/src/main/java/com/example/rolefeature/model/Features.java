package com.example.rolefeature.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table
public class Features {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "featureId")
	private Long featureId;
	
	@Column(name = "FeatureName")
	@NotBlank (message = "FeatureName MUST NOT BLANK!")
	private String freaturesName;
	
	@Column(name = "featuresDescription")
	@NotBlank(message = "Description MUST NOT BLANK!")
	private String featuresDescriptoin;
	
	
	
	
}