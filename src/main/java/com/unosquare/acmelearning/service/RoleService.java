package com.unosquare.acmelearning.service;

import java.util.Optional;

import com.unosquare.acmelearning.model.entity.Role;

public interface RoleService {

    public Optional<Role> findByName(String name);
}
