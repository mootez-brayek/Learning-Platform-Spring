package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.StudentProgressRequest;
import com.education.learningplatform.curriculum.dto.StudentProgressResponse;

import java.util.List;

public interface StudentProgressService {

    StudentProgressResponse getMyProgressByObjective(
        Long learningObjectiveId
    );

    List<StudentProgressResponse> getMyProgress();

    StudentProgressResponse initializeProgress(
        StudentProgressRequest request
    );
}
