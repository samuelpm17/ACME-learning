package com.unosquare.acmelearning.service.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSignUpDto {
    private String name;
    private String lastname;
    @NotEmpty
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String parentPhone;
    private String parentName;
}
