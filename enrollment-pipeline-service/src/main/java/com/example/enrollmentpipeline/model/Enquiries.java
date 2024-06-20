package com.example.enrollmentpipeline.model;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import com.example.enrollmentpipeline.dto.Admissions;
import com.example.enrollmentpipeline.dto.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enquiries {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer enquiryId; 
    @NotNull(message = "name is mandatory")
//    @Size(min = 10, message = "Minimum 10 characters required")
    private String name; 
    @NotNull(message = "email is mandatory")
    private String email;
    @NotNull(message = "mobileNo is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number")
    private String mobileNo; 
    @NotNull(message = "enquirySource is mandatory")
    private String enquirySource; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Courses courses ;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name = "pipeLinePhaseId",nullable = false)
    private PipeLinePhases pipeLinePhases ; 
    
    
    //private int salesPersonId;
}
