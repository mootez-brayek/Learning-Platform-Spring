package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProgressRequest {

    @NotNull(message = "{validation.notNull}")
    private Long learningObjectiveId;
}
