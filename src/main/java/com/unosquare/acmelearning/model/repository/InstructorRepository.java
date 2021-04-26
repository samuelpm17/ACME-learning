package com.unosquare.acmelearning.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.Instructor;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
   public Instructor findByPerson_User_Username(String username);
}
