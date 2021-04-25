package com.unosquare.acmelearning.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.model.repository.InstructorRepository;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

@Service
public class InstructorServiceImpl implements InstructorService{

    private InstructorRepository instructorRepository;
	
	@Autowired
	public InstructorServiceImpl(InstructorRepository instructorRepository) {
		this.instructorRepository = instructorRepository;
	}

	@Override
	public List<Instructor> findAll() {
		return (List<Instructor>) instructorRepository.findAll();
	}

	@Override
	public Instructor findById(Long id) {
		return instructorRepository.findById(id).orElse(null);
	}

	@Override
	public Instructor save(InstructorSignUpDto instructorDto) throws ApplicationException {
	    Instructor newInstructor = getInstructorFromSignUpRequest(instructorDto);
		return instructorRepository.save(newInstructor);
	}

	@Override
	public void delete(Long id) {
		instructorRepository.deleteById(id);
	}
	
	private Instructor getInstructorFromSignUpRequest(InstructorSignUpDto instructorDto) throws ApplicationException {
	    Instructor instructor = new Instructor();
	    
	    try {
            BeanUtils.copyProperties(instructor, instructorDto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException("Failed to copy instructor properties from Sign Up request", e);
        }
	    return instructor;
	}
}
