package com.unosquare.acmelearning.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    public Student findByPerson_User_Username(String username);
}
