package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssessmentExerciseResponse {

    private Long id;

    private Long assessmentId;
    private String assessmentTitle;

    private Long exerciseId;
    private String exerciseTitle;

    private Integer exerciseOrder;
    private Integer points;
}
