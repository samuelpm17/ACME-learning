package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.repository.CourseRepository;
import com.unosquare.acmelearning.service.CourseService;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.dto.CourseCreationDto;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;
	private InstructorService instructorService;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
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
	public Course create(CourseCreationDto courseDto) throws BusinessException {
	    Course newCourse = courseRepository.save(new Course(courseDto.getName(), courseDto.getDescription()));
	    instructorService.assingCourseToInstructor(courseDto.getInstructorId(), newCourse);
		return newCourse;
	}

	@Override
	public void delete(Long Id) {
		courseRepository.deleteById(Id);
	}

}
