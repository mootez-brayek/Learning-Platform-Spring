package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ExerciseAttemptRequest;
import com.education.learningplatform.curriculum.dto.ExerciseAttemptResponse;

import java.util.List;

public interface ExerciseAttemptService {

    ExerciseAttemptResponse submitAttempt(
        ExerciseAttemptRequest request
    );

    List<ExerciseAttemptResponse> getMyAttempts();

    List<ExerciseAttemptResponse> getMyAttemptsByObjective(
        Long learningObjectiveId
    );
}
