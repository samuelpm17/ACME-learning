package com.unosquare.acmelearning.service;

import java.util.List;

import com.unosquare.acmelearning.model.entity.User;

public interface UserService {

	public List<User> findAll();

    public User findById(Long id);

    public User findByUsername(String username);

	public User save(User user);

	public void delete(Long id);
	
}
