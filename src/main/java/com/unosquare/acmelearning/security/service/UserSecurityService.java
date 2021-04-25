package com.unosquare.acmelearning.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.entity.User;
import com.unosquare.acmelearning.service.UserService;

@Service
public class UserSecurityService implements UserDetailsService {
	
	private Logger log = LoggerFactory.getLogger(UserSecurityService.class);

    private UserService userService;
	
	@Autowired
	public UserSecurityService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		if(user == null) {
			log.error(String.format("The user %s doesn't exists in the system", username));
			throw new UsernameNotFoundException(String.format("The user %s doesn't exists in the system", username));
		}
		List<Role> roles = new ArrayList<>();
		roles.add(user.getRole());
		
		List<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, authorities);
	}

}