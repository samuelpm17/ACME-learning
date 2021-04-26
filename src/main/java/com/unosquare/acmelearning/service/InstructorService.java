package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

public interface InstructorService {

	public List<Instructor> findAll();

	public Instructor findById(Long id);

	public Instructor save(InstructorSignUpDto instructorDto) throws ApplicationException;

	public void delete(Long id);
	
	public List<Course> findAssignedCourses(String authorization) throws ApplicationException;
	
	public void assingCourseToInstructor(Long instructorId, Course course) throws BusinessException;
}
