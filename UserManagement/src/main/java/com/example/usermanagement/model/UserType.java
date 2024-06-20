package com.example.usermanagement.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class UserType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "userTypeName is a required field")
    @Size(min = 2, message = "userTypeName must be at least 2 characters")
    private String userTypeName;

    private String userTypeDescription;
    
    
    @OneToMany(mappedBy = "id")
    private List<Users> users;
}
