package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.ChapterRequest;
import com.education.learningplatform.curriculum.dto.ChapterResponse;
import com.education.learningplatform.curriculum.service.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChapterResponse createChapter(
        @Valid @RequestBody ChapterRequest request
    ) {
        return chapterService.createChapter(request);
    }

    @GetMapping("/{id}")
    public ChapterResponse getChapterById(
        @PathVariable Long id
    ) {
        return chapterService.getChapterById(id);
    }

    @GetMapping("/level-subject/{levelSubjectId}")
    public List<ChapterResponse> getChaptersByLevelSubject(
        @PathVariable Long levelSubjectId
    ) {
        return chapterService.getChaptersByLevelSubject(
            levelSubjectId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateChapter(
        @PathVariable Long id
    ) {
        chapterService.deactivateChapter(id);
    }
}
