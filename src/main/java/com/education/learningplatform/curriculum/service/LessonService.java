package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.LessonRequest;
import com.education.learningplatform.curriculum.dto.LessonResponse;

import java.util.List;

public interface LessonService {

    LessonResponse createLesson(LessonRequest request);

    LessonResponse getLessonById(Long id);

    List<LessonResponse> getLessonsByLearningObjective(
        Long learningObjectiveId
    );

    void deactivateLesson(Long id);
}
