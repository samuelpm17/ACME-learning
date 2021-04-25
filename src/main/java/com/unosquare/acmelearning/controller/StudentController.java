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

import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/create")
	public Student create(@RequestBody Student student) {
		return studentService.save(student);
	}

	@GetMapping("/list")
	public List<Student> list() {
		return studentService.findAll();
	}

	@GetMapping("/list/{id}")
	public Student findById(@PathVariable Long id) {
		return studentService.findById(id);
	}

	@DeleteMapping("/delete/{id]")
	public void delete(@PathVariable Long id) {
		studentService.delete(id);
	}
}
