package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.service.dto.StudentSignUpDto;

public interface StudentService {

	public List<Student> findAll();

	public Student findById(Long id);

	public void delete(Long id);

    public Student save(StudentSignUpDto studentDto) throws ApplicationException;
    
    public Student findByUsername(String username);

}
