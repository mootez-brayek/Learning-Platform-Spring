package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.User.model.EducationLevel;
import com.education.learningplatform.curriculum.dto.LevelSubjectRequest;
import com.education.learningplatform.curriculum.dto.LevelSubjectResponse;

import java.util.List;

public interface LevelSubjectService {

    LevelSubjectResponse assignSubject(LevelSubjectRequest request);

    List<LevelSubjectResponse> getSubjectsByLevel(
        EducationLevel educationLevel
    );

    void deactivateSubject(Long id);
}
