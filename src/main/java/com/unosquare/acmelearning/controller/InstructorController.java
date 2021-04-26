package com.unosquare.acmelearning.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.RequestBodyValidator;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorService instructorService;
    private RequestBodyValidator requestBodyValidator;

    @Autowired
    public InstructorController(InstructorService instructorService, RequestBodyValidator requestBodyValidator) {
        this.instructorService = instructorService;
        this.requestBodyValidator = requestBodyValidator;
    }

    @GetMapping("/list")
    public List<Instructor> list() {
        return instructorService.findAll();
    }

    @GetMapping("/mycourses")
    public List<Course> listAssignedCourses(@RequestHeader("Authorization") String authorization) throws ApplicationException {
        return instructorService.findAssignedCourses(authorization);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@Valid @RequestBody InstructorSignUpDto instructorDto, BindingResult bindingResult)
            throws ApplicationException {
        if (bindingResult.hasErrors()) {
            return requestBodyValidator.validate(bindingResult);
        }
        Instructor instructor =  instructorService.save(instructorDto);
        return new ResponseEntity<Instructor>(instructor, HttpStatus.CREATED);
    }
}
