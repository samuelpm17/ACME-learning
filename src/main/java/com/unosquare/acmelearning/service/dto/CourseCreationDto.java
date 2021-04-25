package com.unosquare.acmelearning.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreationDto {
    private String name;
    private String description;
    private Long instructorId;
}
