package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.model.entity.Person;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.model.repository.InstructorRepository;
import com.unosquare.acmelearning.security.service.JsonWebTokenService;
import com.unosquare.acmelearning.service.InstructorService;
import com.unosquare.acmelearning.service.RoleService;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;
    private JsonWebTokenService tokenService;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, RoleService roleService,
            BCryptPasswordEncoder passwordEncoder, JsonWebTokenService tokenService) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
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
    public List<Course> findAssignedCourses(String authorization) throws ApplicationException {
        String username = tokenService.getUserNameFromRequest(authorization);
        Instructor instructor = instructorRepository.findByPerson_User_Username(username);
        return instructor.getCourses();
    }

    @Override
    public void assingCourseToInstructor(Long instructorId, Course course) throws BusinessException {
        Instructor instructor = findById(instructorId);

        if (instructor == null) {
            throw new BusinessException(String.format("The instructor with id %s does not exists.", instructorId));
        }

        instructor.getCourses().add(course);
        instructorRepository.save(instructor);
    }

    private Instructor getInstructorFromSignUpRequest(InstructorSignUpDto instructorDto) throws ApplicationException {
        Role role = roleService.findByName("ROLE_INSTRUCTOR")
                .orElseThrow(() -> new ApplicationException("The role 'INSTRUCTOR' does not exists"));

        User newUser = new User(instructorDto.getUsername(), passwordEncoder.encode(instructorDto.getPassword()), role);

        Person newPerson = new Person();
        newPerson.setName(instructorDto.getName());
        newPerson.setLastname(instructorDto.getLastname());
        newPerson.setEmail(instructorDto.getEmail());
        newPerson.setUser(newUser);

        Instructor newInstructor = new Instructor();
        newInstructor.setPerson(newPerson);
        newInstructor.setSalary(instructorDto.getSalary());

        return newInstructor;
    }
}
