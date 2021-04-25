package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Enrollment;
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
	public Enrollment findById(Long id) {
		return enrollmentRepository.findById(id).orElse(null);
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
