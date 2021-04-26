package com.unosquare.acmelearning.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long>{
    
    public List<Enrollment> findByCourse_Id(Long id);

    public Enrollment findByCourse_IdAndStudent_Id(Long courseId, Long studentId);

    public List<Enrollment> findByStudent_Id(Long studentId);
}
