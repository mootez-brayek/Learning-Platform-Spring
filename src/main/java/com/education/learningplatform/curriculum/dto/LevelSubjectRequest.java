package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.User.model.EducationLevel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelSubjectRequest {

    @NotNull(message = "{validation.notNull}")
    private EducationLevel educationLevel;

    @NotNull(message = "{validation.notNull}")
    private Long subjectId;
}
