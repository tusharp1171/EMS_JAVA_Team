package com.example.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.service.UserEducationDetailsService;

@RestController
@RequestMapping("userEducationDetails")
public class UserEducationDetailsController {
@Autowired
UserEducationDetailsService educationDetailsService;
}
