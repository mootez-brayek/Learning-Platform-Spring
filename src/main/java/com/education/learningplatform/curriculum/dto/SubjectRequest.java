package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String name;

    private String description;

    private String icon;
}
