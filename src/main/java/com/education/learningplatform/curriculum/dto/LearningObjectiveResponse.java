package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LearningObjectiveResponse {

    private Long id;
    private String name;
    private String description;
    private Long competencyId;
    private String competencyName;
    private boolean active;
}
