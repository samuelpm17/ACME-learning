package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Person;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.model.repository.StudentRepository;
import com.unosquare.acmelearning.service.RoleService;
import com.unosquare.acmelearning.service.StudentService;
import com.unosquare.acmelearning.service.dto.StudentSignUpDto;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, RoleService roleService,
            BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
    public Student save(StudentSignUpDto studentDto) throws ApplicationException {
        Student newStudent = getStudentFromSignUpRequest(studentDto);
        return studentRepository.save(newStudent);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByPerson_User_Username(username);
    }

    private Student getStudentFromSignUpRequest(StudentSignUpDto studentDto) throws ApplicationException {
        Role role = roleService.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new ApplicationException("The role 'STUDENT' does not exists"));

        User newUser = new User(studentDto.getUsername(), passwordEncoder.encode(studentDto.getPassword()), role);

        Person newPerson = new Person();
        newPerson.setName(studentDto.getName());
        newPerson.setLastname(studentDto.getLastname());
        newPerson.setEmail(studentDto.getEmail());
        newPerson.setUser(newUser);

        Student newStudent = new Student();
        newStudent.setPerson(newPerson);
        newStudent.setParentName(studentDto.getParentName());
        newStudent.setParentPhone(studentDto.getParentPhone());

        return newStudent;
    }

}
