package com.example.usermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Entity
@Data
public class UserAdresses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NotNull
    @Size(min = 1, message = "Address line 1 is required")
    @Column(name = "AddressLine1", nullable = false)
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String addressLine2;
    @NotNull
    @Size(min = 1, message = "City is required")
    private String city;

    @NotNull
    @Size(min = 1, message = "State is required")
    private String state;

    @NotNull
    @Size(min = 5, max = 10, message = "ZIP code should be between 5 and 10 characters")
    private String zipCode;

    @NotNull
    @Size(min = 1, message = "Country is required")
    private String country;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Users user;
}

