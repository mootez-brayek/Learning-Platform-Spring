package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentExerciseRequest {

    @NotNull(message = "{validation.notNull}")
    private Long assessmentId;

    @NotNull(message = "{validation.notNull}")
    private Long exerciseId;

    @NotNull(message = "{validation.notNull}")
    @Min(value = 1, message = "{validation.min}")
    private Integer exerciseOrder;

    @NotNull(message = "{validation.notNull}")
    @Min(value = 1, message = "{validation.min}")
    private Integer points;
}
