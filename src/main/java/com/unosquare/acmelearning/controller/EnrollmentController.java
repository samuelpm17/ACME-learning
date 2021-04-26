package com.unosquare.acmelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	private EnrollmentService enrollmentService;

	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PostMapping("/enroll/course/{courseId}")
    public Enrollment create(@RequestHeader("Authorization") String authorization, @PathVariable Long courseId)
            throws ApplicationException, BusinessException {
		return enrollmentService.enroll(authorization, courseId);
	}

    @GetMapping("/course/{courseId}")
    public List<Student> findById(@PathVariable Long courseId) {
        return enrollmentService.findStudentsByCourseId(courseId);
    }

    @GetMapping("/mycourses")
    public List<Course> findById(@RequestHeader String authorization) throws ApplicationException, BusinessException {
        return enrollmentService.findMyCourses(authorization);
    }

	@DeleteMapping("/drop/{courseId}")
    public void delete(@RequestHeader("Authorization") String authorization, @PathVariable Long courseId)
            throws BusinessException, ApplicationException {
		enrollmentService.drop(authorization, courseId);
	}
}
