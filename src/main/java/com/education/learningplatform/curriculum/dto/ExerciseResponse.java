package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExerciseResponse {

    private Long id;
    private String title;
    private String instruction;
    private ExerciseType type;
    private Integer difficulty;
    private Long learningObjectiveId;
    private String learningObjectiveName;
    private boolean active;
}
