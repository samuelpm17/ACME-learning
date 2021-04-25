package com.unosquare.acmelearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.model.repository.UserRepository;
import com.unosquare.acmelearning.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
