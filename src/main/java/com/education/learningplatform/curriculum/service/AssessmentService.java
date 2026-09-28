package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.AssessmentRequest;
import com.education.learningplatform.curriculum.dto.AssessmentResponse;

import java.util.List;

public interface AssessmentService {

    AssessmentResponse createAssessment(
        AssessmentRequest request
    );

    AssessmentResponse getAssessmentById(
        Long id
    );

    List<AssessmentResponse> getAssessmentsByLearningObjective(
        Long learningObjectiveId
    );

    void deactivateAssessment(
        Long id
    );
}
