package com.unosquare.acmelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.service.CourseService;
import com.unosquare.acmelearning.service.dto.CourseCreationDto;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/list")
	public List<Course> listAll(){
		return courseService.findAll();
	}

	@PostMapping("/create")
	public Course create(@RequestBody CourseCreationDto courseDto) throws BusinessException {
		return courseService.create(courseDto);
	}

}
