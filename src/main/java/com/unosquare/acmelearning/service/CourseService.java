package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.model.entity.Course;

public interface CourseService {

	public List<Course> findAll();
	public Course findById(Long id);
	public Course create(Course course);
	public void delete(Long Id);
	
}
