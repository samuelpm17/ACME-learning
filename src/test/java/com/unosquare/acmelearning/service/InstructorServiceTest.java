package com.unosquare.acmelearning.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.model.entity.Instructor;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.repository.InstructorRepository;
import com.unosquare.acmelearning.security.service.JsonWebTokenService;
import com.unosquare.acmelearning.service.dto.InstructorSignUpDto;
import com.unosquare.acmelearning.service.impl.InstructorServiceImpl;

class InstructorServiceTest {

    @InjectMocks
    private InstructorServiceImpl instructorService;
    
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private JsonWebTokenService tokenService;
    
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        instructorService = new InstructorServiceImpl(instructorRepository, roleService, passwordEncoder, tokenService);
    }
    
    @Test
    void whenSigningUpAndInstructorRoleDoesNotExistsShouldThrowException() {
        //Arrange
        InstructorSignUpDto testInstructorDto = new InstructorSignUpDto();
        
        when(roleService.findByName("ROLE_INSTRUCTOR")).thenReturn(Optional.empty());
        
        //Act | Assert
        assertThrows(ApplicationException.class, () -> instructorService.save(testInstructorDto));
    }
    
    @Test
    void whenSigningUpShouldSaveNewInstructor() throws ApplicationException {
        //Arrange
        InstructorSignUpDto testInstructorDto = new InstructorSignUpDto();
        Role testrole = new Role();
        
        when(roleService.findByName("ROLE_INSTRUCTOR")).thenReturn(Optional.of(testrole));
        
        //Act
        instructorService.save(testInstructorDto);
        
        //Assert
        verify(instructorRepository).save(any());
    }
    
    @Test
    void whenFindingAssignedCoursesShouldFindInstructorByUsername() throws ApplicationException {
        //Arrange
        String testAuthorization = "authorization";
        String testUsername = "username";
        Instructor testInstructor = new Instructor();
        
        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(instructorRepository.findByPerson_User_Username(testUsername)).thenReturn(testInstructor);
        
        //Act
        instructorService.findAssignedCourses(testAuthorization);
        
        //Assert
        verify(instructorRepository).findByPerson_User_Username(testUsername);
    }

}
