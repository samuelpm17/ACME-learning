package com.unosquare.acmelearning.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.service.RequestBodyValidator;
import com.unosquare.acmelearning.service.StudentService;
import com.unosquare.acmelearning.service.dto.StudentSignUpDto;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private RequestBodyValidator requestBodyValidator;

    @Autowired
    public StudentController(StudentService studentService, RequestBodyValidator requestBodyValidator) {
        this.studentService = studentService;
        this.requestBodyValidator = requestBodyValidator;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@Valid @RequestBody StudentSignUpDto studentDto, BindingResult bindingResult) throws ApplicationException {
        if (bindingResult.hasErrors()) {
            return requestBodyValidator.validate(bindingResult);
        }        
        Student newStudent = studentService.save(studentDto);
        return new ResponseEntity<Student>(newStudent, HttpStatus.CREATED);
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
