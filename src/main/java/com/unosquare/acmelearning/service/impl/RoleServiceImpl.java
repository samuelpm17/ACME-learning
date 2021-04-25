package com.unosquare.acmelearning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.model.entity.Role;
import com.unosquare.acmelearning.model.repository.RoleRepository;
import com.unosquare.acmelearning.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
     
    @Override
    public Optional<Role> findByName(String name) {
        return Optional.of(roleRepository.findByName(name));
    }

}
