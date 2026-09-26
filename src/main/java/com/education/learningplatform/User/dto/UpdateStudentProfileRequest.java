package com.education.learningplatform.User.dto;

import com.education.learningplatform.User.model.EducationLevel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateStudentProfileRequest {

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private EducationLevel educationLevel;

    private String avatar;

}
