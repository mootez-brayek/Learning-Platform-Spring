package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.LearningObjectiveRequest;
import com.education.learningplatform.curriculum.dto.LearningObjectiveResponse;

import java.util.List;

public interface LearningObjectiveService {

    LearningObjectiveResponse createObjective(
        LearningObjectiveRequest request
    );

    LearningObjectiveResponse getObjectiveById(Long id);

    List<LearningObjectiveResponse> getObjectivesByCompetency(
        Long competencyId
    );

    void deactivateObjective(Long id);
}
