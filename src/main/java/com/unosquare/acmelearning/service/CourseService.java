package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.service.dto.CourseCreationDto;

public interface CourseService {

	public List<Course> findAll();
	public Course findById(Long id);
	public Course create(CourseCreationDto courseDto) throws BusinessException;
	public void cancel(Long id) throws BusinessException;
	public void startCourse(Long id) throws BusinessException;
	
}
