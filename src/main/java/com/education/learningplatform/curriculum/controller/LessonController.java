package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.LessonRequest;
import com.education.learningplatform.curriculum.dto.LessonResponse;
import com.education.learningplatform.curriculum.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponse createLesson(
        @Valid @RequestBody LessonRequest request
    ) {
        return lessonService.createLesson(request);
    }

    @GetMapping("/{id}")
    public LessonResponse getLessonById(
        @PathVariable Long id
    ) {
        return lessonService.getLessonById(id);
    }

    @GetMapping("/learning-objective/{learningObjectiveId}")
    public List<LessonResponse> getLessonsByLearningObjective(
        @PathVariable Long learningObjectiveId
    ) {
        return lessonService.getLessonsByLearningObjective(
            learningObjectiveId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateLesson(
        @PathVariable Long id
    ) {
        lessonService.deactivateLesson(id);
    }
}
