package com.unosquare.acmelearning.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorSignUpDto {
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private Long salary;
}
