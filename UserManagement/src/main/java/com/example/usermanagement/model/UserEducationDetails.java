package com.example.usermanagement.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UserEducationDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEducationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserId", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "EducationTitle")
    private String educationTitle;

    @Column(name = "Description")
    private String description;

    @Column(name = "PassingYear")
    private String passingYear;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;
}
