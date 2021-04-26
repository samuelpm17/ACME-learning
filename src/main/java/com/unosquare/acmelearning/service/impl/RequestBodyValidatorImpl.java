package com.unosquare.acmelearning.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.unosquare.acmelearning.service.RequestBodyValidator;

@Component
public class RequestBodyValidatorImpl implements RequestBodyValidator {

    @Override
    public ResponseEntity<?> validate(BindingResult bindingResult) {
        Map<String, List<String>> response = new HashMap<>();
        List<String> errorList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(
                error -> errorList.add("Error on field " + error.getField() + ": " + error.getDefaultMessage()));
        response.put("errors", errorList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
