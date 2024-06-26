package com.sn.login.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import com.sn.login.dto.UserAddressDto;
import com.sn.login.dto.UserEducationDetails;


@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 10)
    private String mobile;
    @Transient
    private UserEducationDetails userEducationDetails;
    @Transient
    private UserAddressDto userAddressDto;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password, String mobile,UserEducationDetails userEducationDetails,UserAddressDto userAddressDto) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile=mobile;
        this.userAddressDto=userAddressDto;
        this.userEducationDetails=userEducationDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserEducationDetails getUserEducationDetails() {
        return userEducationDetails;
    }

    public void setUserEducationDetails(UserEducationDetails userEducationDetails) {
        this.userEducationDetails = userEducationDetails;
    }

    public UserAddressDto getUserAddressDto() {
        return userAddressDto;
    }

    public void setUserAddressDto(UserAddressDto userAddressDto) {
        this.userAddressDto = userAddressDto;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}