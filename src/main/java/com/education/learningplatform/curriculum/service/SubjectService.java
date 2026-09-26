package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.SubjectRequest;
import com.education.learningplatform.curriculum.dto.SubjectResponse;

import java.util.List;

public interface SubjectService {

    SubjectResponse createSubject(SubjectRequest request);

    SubjectResponse getSubjectById(Long id);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse updateSubject(Long id, SubjectRequest request);

    void deleteSubject(Long id);
}
