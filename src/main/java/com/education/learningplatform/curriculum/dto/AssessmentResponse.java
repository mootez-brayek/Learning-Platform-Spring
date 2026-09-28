package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.AssessmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssessmentResponse {

    private Long id;
    private String title;
    private String description;
    private AssessmentType type;
    private Integer passingScore;
    private Long learningObjectiveId;
    private String learningObjectiveName;
    private boolean active;
}
