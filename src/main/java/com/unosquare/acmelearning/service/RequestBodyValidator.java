package com.unosquare.acmelearning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface RequestBodyValidator {
    public ResponseEntity<?> validate(BindingResult bindingResult);
}
