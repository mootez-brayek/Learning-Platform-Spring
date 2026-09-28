package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.AssessmentExerciseRequest;
import com.education.learningplatform.curriculum.dto.AssessmentExerciseResponse;

import java.util.List;

public interface AssessmentExerciseService {

    AssessmentExerciseResponse addExerciseToAssessment(
        AssessmentExerciseRequest request
    );

    List<AssessmentExerciseResponse> getExercisesByAssessment(
        Long assessmentId
    );

    void deactivateExerciseFromAssessment(Long id);
}
