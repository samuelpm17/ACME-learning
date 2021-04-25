package com.unosquare.acmelearning.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
