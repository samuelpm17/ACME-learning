package com.unosquare.acmelearning.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.repository.StudentRepository;
import com.unosquare.acmelearning.service.dto.StudentSignUpDto;
import com.unosquare.acmelearning.service.impl.StudentServiceImpl;

class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentRepository, roleService, passwordEncoder);
    }

    @Test
    void whenSigningUpAstudentAndRoleDoesNotExistsShouldThrowException() {
        // Arrange
        StudentSignUpDto testStudentDto = new StudentSignUpDto();

        when(roleService.findByName("ROLE_STUDENT")).thenReturn(Optional.empty());

        // Act | Assert
        assertThrows(ApplicationException.class, () -> studentService.save(testStudentDto));
    }

    @Test
    void whenSigningUShouldSaveNewStudent() throws ApplicationException {
        // Arrange
        StudentSignUpDto testStudentDto = new StudentSignUpDto();
        testStudentDto.setPassword("password");
        Role testRole = new Role();

        when(roleService.findByName("ROLE_STUDENT")).thenReturn(Optional.of(testRole));
        when(passwordEncoder.encode(testStudentDto.getPassword())).thenReturn("encrypted_password");

        // Act
        studentService.save(testStudentDto);

        // Assert
        verify(studentRepository).save(any());
    }

}
