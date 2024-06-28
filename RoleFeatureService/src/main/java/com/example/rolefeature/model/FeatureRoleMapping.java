package com.example.rolefeature.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class FeatureRoleMapping {

	@Id
	@Column(name = "featureRoleMappingId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "featureId")
	private Long featureId;
	
	@Column(name = "roleId")
	private long roleId;
	
//	 @ManyToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "featureId")
////	    @NotNull(message = "Course type is mandatory")
//	    private Features features;
//	
}
