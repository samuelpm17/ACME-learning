package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.model.repository.StudentRepository;
import com.unosquare.acmelearning.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> findAll() {
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public Student findById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}

}
