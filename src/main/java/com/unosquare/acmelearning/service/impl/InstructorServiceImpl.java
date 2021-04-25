package com.unosquare.acmelearning.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.model.repository.InstructorRepository;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.RoleService;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, RoleService roleService,
            BCryptPasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
    
    @Override
    public void assingCourseToInstructor(Long instructorId, Course course) throws BusinessException {
        Instructor instructor = findById(instructorId);
        
        if(instructor == null) {
            throw new BusinessException(String.format("The instructor with id %s does not exists.", instructorId));
        }
        
        instructor.getCourses().add(course);
        instructorRepository.save(instructor);
    }

    private Instructor getInstructorFromSignUpRequest(InstructorSignUpDto instructorDto) throws ApplicationException {
        Instructor newInstructor = new Instructor();
        try {
            BeanUtils.copyProperties(newInstructor, instructorDto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException("Failed to copy instructor properties from Sign Up request", e);
        }

        Role role = roleService.findByName("ROLE_INSTRUCTOR")
                .orElseThrow(() -> new ApplicationException("The role 'INSTRUCTOR' does not exists"));

        User newUser = new User(instructorDto.getUsername(), passwordEncoder.encode(instructorDto.getPassword()), role);

        newInstructor.setUser(newUser);

        return newInstructor;
    }
}
