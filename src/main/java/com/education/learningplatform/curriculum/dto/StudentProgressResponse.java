package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StudentProgressResponse {

    private Long id;

    private Long studentId;

    private Long learningObjectiveId;

    private String learningObjectiveName;

    private Integer exercisesAttempted;

    private Integer exercisesCorrect;

    private Double masteryPercentage;

    private LocalDateTime lastActivityAt;
}
