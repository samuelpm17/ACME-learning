package com.unosquare.acmelearning.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.acmelearning.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}
