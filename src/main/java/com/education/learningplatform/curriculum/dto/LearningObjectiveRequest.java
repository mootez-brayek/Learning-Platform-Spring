package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearningObjectiveRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String name;

    private String description;

    @NotNull(message = "{validation.notNull}")
    private Long competencyId;
}
