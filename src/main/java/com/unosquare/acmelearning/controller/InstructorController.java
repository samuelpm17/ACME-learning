package com.unosquare.acmelearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

	private InstructorService instructorService;

	@Autowired
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
	
	@GetMapping("/list")
	public List<Instructor> list(){
	    return instructorService.findAll();
	}

	@PostMapping("/signup")
	public Instructor create(@RequestBody InstructorSignUpDto instructorDto) throws ApplicationException {
		return instructorService.save(instructorDto);
	}
}
