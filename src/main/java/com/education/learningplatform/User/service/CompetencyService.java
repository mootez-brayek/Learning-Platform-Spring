package com.education.learningplatform.User.service;

import com.education.learningplatform.curriculum.dto.CompetencyRequest;
import com.education.learningplatform.curriculum.dto.CompetencyResponse;

import java.util.List;

public interface CompetencyService {

    CompetencyResponse createCompetency(CompetencyRequest request);

    CompetencyResponse getCompetencyById(Long id);

    List<CompetencyResponse> getCompetenciesByChapter(Long chapterId);

    void deactivateCompetency(Long id);
}
