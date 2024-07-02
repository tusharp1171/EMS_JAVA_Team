package com.example.facultytimetable.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "week_days")
public class WeekDays {

    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer weekDayId;

    @NotBlank(message = "WeekDay name is required")
    private String weekDayName;
 
    @OneToOne(mappedBy = "weekDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private TimeTable timeTable;
    
    public WeekDays(String weekdayName2) {
    	
    	this.weekDayName = weekdayName2;// TODO Auto-generated constructor stub
	}

	public WeekDays() {
		// TODO Auto-generated constructor stub
	}

   
}
