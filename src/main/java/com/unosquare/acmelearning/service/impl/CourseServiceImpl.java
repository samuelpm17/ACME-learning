package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.repository.CourseRepository;
import com.unosquare.acmelearning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> findAll() {
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	@Override
	public Course create(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void delete(Long Id) {
		courseRepository.deleteById(Id);
	}

}
