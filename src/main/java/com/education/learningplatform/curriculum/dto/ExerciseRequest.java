package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.ExerciseType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String title;

    private String instruction;

    @NotNull(message = "{validation.notNull}")
    private ExerciseType type;

    @NotNull(message = "{validation.notNull}")
    @Min(value = 1, message = "{validation.min}")
    @Max(value = 5, message = "{validation.max}")
    private Integer difficulty;

    @NotNull(message = "{validation.notNull}")
    private Long learningObjectiveId;
}
