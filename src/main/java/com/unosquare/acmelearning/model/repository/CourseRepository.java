package com.unosquare.acmelearning.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
