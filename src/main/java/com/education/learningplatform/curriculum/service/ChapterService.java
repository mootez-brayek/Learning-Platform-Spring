package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ChapterRequest;
import com.education.learningplatform.curriculum.dto.ChapterResponse;

import java.util.List;

public interface ChapterService {

    ChapterResponse createChapter(ChapterRequest request);

    ChapterResponse getChapterById(Long id);

    List<ChapterResponse> getChaptersByLevelSubject(Long levelSubjectId);

    void deactivateChapter(Long id);
}
