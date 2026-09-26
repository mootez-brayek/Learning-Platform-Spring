package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.User.model.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LevelSubjectResponse {

    private Long id;
    private EducationLevel educationLevel;
    private Long subjectId;
    private String subjectName;
    private boolean active;
}
