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
    
    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Courses courses ;
    
    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "pipeLinePhaseId",nullable = false)
    private PipeLinePhases pipeLinePhases ;
    
    

	public Enquiries(@NotNull(message = "name is mandatory") String name,
			@NotNull(message = "email is mandatory") String email,
			@NotNull(message = "mobileNo is mandatory") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number") String mobileNo,
			@NotNull(message = "enquirySource is mandatory") String enquirySource, Courses courses,
			PipeLinePhases pipeLinePhases, int salesPersonId) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.enquirySource = enquirySource;
		this.courses = courses;
		this.pipeLinePhases = pipeLinePhases;
		this.salesPersonId = salesPersonId;
	}
	
	
	

	public Enquiries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getEnquiryId() {
		return enquiryId;
	}

//	public void setEnquiryId(Integer enquiryId) {
//		this.enquiryId = enquiryId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEnquirySource() {
		return enquirySource;
	}

	public void setEnquirySource(String enquirySource) {
		this.enquirySource = enquirySource;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public PipeLinePhases getPipeLinePhases() {
		return pipeLinePhases;
	}

	public void setPipeLinePhases(PipeLinePhases pipeLinePhases) {
		this.pipeLinePhases = pipeLinePhases;
	} 
    
    
    private int salesPersonId;
    
}
