package com.unosquare.acmelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	private EnrollmentService enrollmentService;

	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PostMapping("/create")
	public Enrollment create(@RequestBody Enrollment enrollment) {
		return enrollmentService.save(enrollment);
	}

	@GetMapping("/list")
	public List<Enrollment> list() {
		return enrollmentService.findAll();
	}

	@GetMapping("/list/{id}")
	public Enrollment findById(@PathVariable Long id) {
		return enrollmentService.findById(id);
	}

	@DeleteMapping("/delete/{id]")
	public void delete(@PathVariable Long id) {
		enrollmentService.delete(id);
	}
}
