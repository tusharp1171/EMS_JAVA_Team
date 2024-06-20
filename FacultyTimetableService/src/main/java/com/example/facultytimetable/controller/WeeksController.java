package com.example.facultytimetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.facultytimetable.serviceImpl.WeekServiceImpl;

@RestController
@RequestMapping("api/week-days")
public class WeeksController {

	@Autowired
	private WeekServiceImpl weekService;
	
	@GetMapping("{weekDayName}")
	public void getAllData(@PathVariable String weekDayName) {
		
		this.weekService.findWeekData(weekDayName);
		
	}
	
}
