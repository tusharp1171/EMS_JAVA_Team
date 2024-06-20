package com.example.facultytimetable.controller;

import org.apache.hc.client5.http.auth.AuthStateCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.RestController;

import com.example.facultytimetable.service.WeekService;
import com.example.facultytimetable.serviceImpl.WeekServiceImpl;

@RestController
public class WeeksController {

	@Autowired
	private WeekServiceImpl weekService;
	
}
