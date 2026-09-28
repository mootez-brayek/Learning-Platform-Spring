package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.AssessmentType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String title;

    private String description;

    @NotNull(message = "{validation.notNull}")
    private AssessmentType type;

    @NotNull(message = "{validation.notNull}")
    @Min(value = 0, message = "{validation.min}")
    @Max(value = 100, message = "{validation.max}")
    private Integer passingScore;

    @NotNull(message = "{validation.notNull}")
    private Long learningObjectiveId;
}
