package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.model.entity.Student;

public interface EnrollmentService {

	public List<Student> findStudentsByCourseId(Long courseId);

	public Enrollment enroll(String authorization, Long courseId) throws ApplicationException, BusinessException;

	public void drop(String authorization, Long courseId) throws BusinessException, ApplicationException;
	
	public List<Course> findMyCourses(String authorization) throws ApplicationException, BusinessException;

}
