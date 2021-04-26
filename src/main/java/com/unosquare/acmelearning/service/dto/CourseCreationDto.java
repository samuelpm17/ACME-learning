package com.unosquare.acmelearning.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreationDto {
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Long instructorId;
}
