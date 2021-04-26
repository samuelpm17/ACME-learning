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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.service.CourseService;
import com.unosquare.acmelearning.service.RequestBodyValidator;
import com.unosquare.acmelearning.service.dto.CourseCreationDto;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    private RequestBodyValidator requestBodyValidator;

    @Autowired
    public CourseController(CourseService courseService, RequestBodyValidator requestBodyValidator) {
        this.courseService = courseService;
        this.requestBodyValidator = requestBodyValidator;
    }

    @GetMapping("/list")
    public List<Course> listAll() {
        return courseService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CourseCreationDto courseDto, BindingResult bindingResult)
            throws BusinessException {
        if (bindingResult.hasErrors()) {
            return requestBodyValidator.validate(bindingResult);
        }
        Course course = courseService.create(courseDto);
        return new ResponseEntity<Course>(course, HttpStatus.CREATED);
    }

}
