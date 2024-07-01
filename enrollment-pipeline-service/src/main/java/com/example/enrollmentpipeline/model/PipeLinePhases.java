package com.example.enrollmentpipeline.model;
//helo tudshsrs 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PipeLinePhases {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)           
     private Integer pipeLinePhaseId ; 
     @NotNull(message = "phaseName is mandatory")
     private String phaseName;
	public Integer getPipeLinePhaseId() {
		return pipeLinePhaseId;
	}
	public void setPipeLinePhaseId(Integer pipeLinePhaseId) {
		this.pipeLinePhaseId = pipeLinePhaseId;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	} 

}
