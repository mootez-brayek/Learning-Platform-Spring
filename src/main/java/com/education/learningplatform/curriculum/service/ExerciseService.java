package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ExerciseRequest;
import com.education.learningplatform.curriculum.dto.ExerciseResponse;

import java.util.List;

public interface ExerciseService {

    ExerciseResponse createExercise(ExerciseRequest request);

    ExerciseResponse getExerciseById(Long id);

    List<ExerciseResponse> getExercisesByLearningObjective(
        Long learningObjectiveId
    );

    void deactivateExercise(Long id);
}
