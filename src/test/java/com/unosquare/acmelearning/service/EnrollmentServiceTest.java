package com.unosquare.acmelearning.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.model.entity.Person;
import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.model.repository.EnrollmentRepository;
import com.unosquare.acmelearning.security.service.JsonWebTokenService;
import com.unosquare.acmelearning.service.impl.EnrollmentServiceImpl;

class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private CourseService courseService;
    @Mock
    private JsonWebTokenService tokenService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        enrollmentService = new EnrollmentServiceImpl(enrollmentRepository, tokenService, studentService,
                courseService);
    }

    @Test
    void whenFindingMyCoursesAndStudentDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        String testAuthorization = "test";
        String testUsername = "username";

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.findMyCourses(testAuthorization));
    }

    @Test
    void whenFindingMyCoursesShouldFindByStudentId() throws ApplicationException, BusinessException {
        // Arrange
        String testAuthorization = "test";
        String testUsername = "username";
        Student testStudent = new Student();

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);

        // Act
        enrollmentService.findMyCourses(testAuthorization);

        // Assert
        verify(enrollmentRepository).findByStudent_Id(any());
    }

    @Test
    void whenDropingACoursesAndStudentDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.drop(testAuthorization, testCourseId));
    }

    @Test
    void whenDropingACoursesAndItDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Student testStudent = new Student();

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.drop(testAuthorization, testCourseId));
    }

    @Test
    void whenDropingACoursesAndEnrollmentDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Course testCourse = new Course();
        User testUser = new User(testUsername, "password", new Role());
        Person testPerson = new Person();
        testPerson.setUser(testUser);
        Student testStudent = new Student();
        testStudent.setPerson(testPerson);

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(testCourse);
        when(enrollmentRepository.findByCourse_IdAndStudent_Id(any(), any())).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.drop(testAuthorization, testCourseId));
    }

    @Test
    void whenDropingACourseAndEnrollmentExistsShouldDeleteIt() throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Course testCourse = new Course();
        User testUser = new User(testUsername, "password", new Role());
        Person testPerson = new Person();
        testPerson.setUser(testUser);
        Student testStudent = new Student();
        testStudent.setPerson(testPerson);
        Enrollment testEnrollment = new Enrollment();

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(testCourse);
        when(enrollmentRepository.findByCourse_IdAndStudent_Id(any(), any())).thenReturn(testEnrollment);

        // Act
        enrollmentService.drop(testAuthorization, testCourseId);

        // Assert
        verify(enrollmentRepository).deleteById(any());
    }

    @Test
    void whenEnrollingToCourseAndStudentDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.enroll(testAuthorization, testCourseId));
    }

    @Test
    void whenEnrollingToCoursesAndItDoesNotExistsShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Student testStudent = new Student();

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(null);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.enroll(testAuthorization, testCourseId));
    }

    @Test
    void whenEnrollingToStartedCoursesShouldThrowBusinessException()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Student testStudent = new Student();
        Course testCourse = new Course();
        testCourse.setStarted(true);

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(testCourse);

        // Act | Assert
        assertThrows(BusinessException.class, () -> enrollmentService.enroll(testAuthorization, testCourseId));
    }

    @Test
    void whenEnrollingToNonStartedCourseShouldSaveNewEnrollment()
            throws ApplicationException, BusinessException {
        // Arrange
        Long testCourseId = 1L;
        String testAuthorization = "test";
        String testUsername = "username";
        Student testStudent = new Student();
        Course testCourse = new Course();

        when(tokenService.getUserNameFromRequest(testAuthorization)).thenReturn(testUsername);
        when(studentService.findByUsername(testUsername)).thenReturn(testStudent);
        when(courseService.findById(testCourseId)).thenReturn(testCourse);

        // Act
        enrollmentService.enroll(testAuthorization, testCourseId);
        
        //Assert
        verify(enrollmentRepository).save(any());
    }

}
