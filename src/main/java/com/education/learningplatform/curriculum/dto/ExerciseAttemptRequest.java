package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseAttemptRequest {

    @NotNull(message = "{validation.notNull}")
    private Long exerciseId;

    @NotNull(message = "{validation.notNull}")
    private String answer;
}
