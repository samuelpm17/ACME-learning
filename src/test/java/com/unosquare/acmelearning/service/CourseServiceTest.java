package com.unosquare.acmelearning.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.repository.CourseRepository;
import com.unosquare.acmelearning.service.dto.CourseCreationDto;
import com.unosquare.acmelearning.service.impl.CourseServiceImpl;

class CourseServiceTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private InstructorService instructorService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        courseService = new CourseServiceImpl(courseRepository, instructorService);
    }

    @Test
    void whenStartingACourseAndItDoesNotExistsShouldThrowBusinessException() {
        // Arrange
        Long testCourseId = 1L;
        when(courseRepository.findById(testCourseId)).thenReturn(Optional.empty());

        // Act | Assert
        Assertions.assertThrows(BusinessException.class, () -> courseService.startCourse(testCourseId));
    }

    @Test
    void whenStartingAStartedCourseShouldThrowBusinessException() {
        // Arrange
        Long testCourseId = 1L;
        Course testCourse = new Course();
        testCourse.setStarted(true);

        when(courseRepository.findById(testCourseId)).thenReturn(Optional.of(testCourse));

        // Act | Assert
        Assertions.assertThrows(BusinessException.class, () -> courseService.startCourse(testCourseId));
    }

    @Test
    void whenStartingANonStartedCourseShouldSaveTheCourse() throws BusinessException {
        // Arrange
        Long testCourseId = 1L;
        Course testCourse = new Course();
        testCourse.setStarted(false);

        when(courseRepository.findById(testCourseId)).thenReturn(Optional.of(testCourse));

        // Act
        courseService.startCourse(testCourseId);

        // Assert
        verify(courseRepository).save(testCourse);
    }

    @Test
    void whenCancelingACourseAndItDoesNotExistsShouldThrowBusinessException() {
        // Arrange
        Long testCourseId = 1L;
        when(courseRepository.findById(testCourseId)).thenReturn(Optional.empty());

        // Act | Assert
        Assertions.assertThrows(BusinessException.class, () -> courseService.cancel(testCourseId));
    }

    @Test
    void whenCancelingAStartedCourseShouldThrowBusinessException() {
        // Arrange
        Long testCourseId = 1L;
        Course testCourse = new Course();
        testCourse.setStarted(true);

        when(courseRepository.findById(testCourseId)).thenReturn(Optional.of(testCourse));

        // Act | Assert
        Assertions.assertThrows(BusinessException.class, () -> courseService.cancel(testCourseId));
    }

    @Test
    void whenCancelingANonStartedCourseShouldSaveTheCourse() throws BusinessException {
        // Arrange
        Long testCourseId = 1L;
        Course testCourse = new Course();
        testCourse.setStarted(false);

        when(courseRepository.findById(testCourseId)).thenReturn(Optional.of(testCourse));

        // Act
        courseService.cancel(testCourseId);

        // Assert
        verify(courseRepository).save(testCourse);
    }

    @Test
    void whenCreatingNewCourseShouldAssingCourseToInstructor() throws BusinessException {
        //Arrange
        CourseCreationDto testCourseDto = new CourseCreationDto();
        Course newCourse = new Course();
        
        when(courseRepository.save(newCourse)).thenReturn(newCourse);
        
        //Act
        courseService.create(testCourseDto);
        
        //Assert
        verify(instructorService).assingCourseToInstructor(any(), any());
    }
}
