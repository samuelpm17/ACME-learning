package com.unosquare.acmelearning.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.model.repository.EnrollmentRepository;
import com.unosquare.acmelearning.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private EnrollmentRepository enrollmentRepository;
	
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public List<Enrollment> findAll() {
		return (List<Enrollment>) enrollmentRepository.findAll();
	}

	@Override
	public List<Student> findStudentsByCourseId(Long courseId) {
	    List<Enrollment> enrollmentsList = enrollmentRepository.findByCourse_Id(courseId);
	    if(enrollmentsList == null || enrollmentsList.isEmpty()) {
	        return null;
	    }
	    List<Student> studentsList = enrollmentsList.stream().map(enrollment -> enrollment.getStudent()).collect(Collectors.toList());
		return studentsList;
	}

	@Override
	public Enrollment save(Enrollment enrollment) {
		return enrollmentRepository.save(enrollment);
	}

	@Override
	public void delete(Long id) {
		enrollmentRepository.deleteById(id);
	}

}
