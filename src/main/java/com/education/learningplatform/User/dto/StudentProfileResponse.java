package com.education.learningplatform.User.dto;

import com.education.learningplatform.User.model.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudentProfileResponse {

    private Long id;
    private LocalDate dateOfBirth;
    private EducationLevel educationLevel;
    private String avatar;
}
