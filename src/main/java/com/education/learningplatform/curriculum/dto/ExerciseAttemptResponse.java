package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExerciseAttemptResponse {

    private Long id;

    private Long exerciseId;
    private String exerciseTitle;

    private Long learningObjectiveId;
    private String learningObjectiveName;

    private boolean correct;

    private LocalDateTime attemptedAt;
}
