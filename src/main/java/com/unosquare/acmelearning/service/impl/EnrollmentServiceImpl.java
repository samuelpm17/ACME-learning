package com.unosquare.acmelearning.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;
import com.unosquare.acmelearning.exception.BusinessException;
import com.unosquare.acmelearning.model.entity.Course;
import com.unosquare.acmelearning.model.entity.Enrollment;
import com.unosquare.acmelearning.model.entity.Student;
import com.unosquare.acmelearning.model.repository.EnrollmentRepository;
import com.unosquare.acmelearning.security.service.JsonWebTokenService;
import com.unosquare.acmelearning.service.CourseService;
import com.unosquare.acmelearning.service.EnrollmentService;
import com.unosquare.acmelearning.service.StudentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final String ERROR_STUDENT_NOT_FOUND = "Could not find any student with username %s";
    private static final String ERROR_COURSE_NOT_FOUND = "Could not find any course with id %s";
    
    private EnrollmentRepository enrollmentRepository;
    private StudentService studentService;
    private CourseService courseService;
    private JsonWebTokenService tokenService;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, JsonWebTokenService tokenService,
            StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.tokenService = tokenService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) {
        List<Enrollment> enrollmentsList = enrollmentRepository.findByCourse_Id(courseId);
        if (enrollmentsList == null || enrollmentsList.isEmpty()) {
            return null;
        }
        List<Student> studentsList = enrollmentsList.stream().map(enrollment -> enrollment.getStudent())
                .collect(Collectors.toList());
        return studentsList;
    }

    @Override
    public Enrollment enroll(String authorization, Long courseId) throws ApplicationException, BusinessException {
        String username = tokenService.getUserNameFromRequest(authorization);
        Student student = studentService.findByUsername(username);
        if (student == null) {
            throw new BusinessException(String.format(ERROR_STUDENT_NOT_FOUND, username));
        }

        Course course = courseService.findById(courseId);

        if (course == null) {
            throw new BusinessException(String.format(ERROR_COURSE_NOT_FOUND, courseId));
        }

        if (course.isStarted()) {
            throw new BusinessException("It is not possible to enroll in a course that has already started");
        }

        Enrollment newEnrollment = new Enrollment(course, student);

        return enrollmentRepository.save(newEnrollment);
    }

    @Override
    public void drop(String authorization, Long courseId) throws BusinessException, ApplicationException {
        String username = tokenService.getUserNameFromRequest(authorization);
        Student student = studentService.findByUsername(username);
        if (student == null) {
            throw new BusinessException(String.format(ERROR_STUDENT_NOT_FOUND, username));
        }

        Course course = courseService.findById(courseId);

        if (course == null) {
            throw new BusinessException(String.format(ERROR_COURSE_NOT_FOUND, courseId));
        }

        Enrollment enrollment = enrollmentRepository.findByCourse_IdAndStudent_Id(course.getId(), student.getId());

        if (enrollment == null) {
            throw new BusinessException(String.format("Could not find any enrollment of %s in the course %s",
                    student.getPerson().getUser().getUsername(), course.getName()));
        }

        enrollmentRepository.deleteById(enrollment.getId());
    }

    @Override
    public List<Course> findMyCourses(String authorization) throws ApplicationException, BusinessException {
        String username = tokenService.getUserNameFromRequest(authorization);
        Student student = studentService.findByUsername(username);

        if (student == null) {
            throw new BusinessException(String.format(ERROR_STUDENT_NOT_FOUND, username));
        }

        List<Enrollment> enrollmentsList = enrollmentRepository.findByStudent_Id(student.getId());

        List<Course> coursesList = enrollmentsList.stream().map(enrollment -> enrollment.getCourse())
                .collect(Collectors.toList());

        return coursesList;
    }

}
