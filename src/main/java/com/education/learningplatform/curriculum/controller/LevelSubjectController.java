package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.User.model.EducationLevel;
import com.education.learningplatform.curriculum.dto.LevelSubjectRequest;
import com.education.learningplatform.curriculum.dto.LevelSubjectResponse;
import com.education.learningplatform.curriculum.service.LevelSubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/level-subjects")
@RequiredArgsConstructor
public class LevelSubjectController {

    private final LevelSubjectService levelSubjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelSubjectResponse assignSubject(
        @Valid @RequestBody LevelSubjectRequest request
    ) {
        return levelSubjectService.assignSubject(request);
    }

    @GetMapping("/{educationLevel}")
    public List<LevelSubjectResponse> getSubjectsByLevel(
        @PathVariable EducationLevel educationLevel
    ) {
        return levelSubjectService.getSubjectsByLevel(
            educationLevel
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateSubject(
        @PathVariable Long id
    ) {
        levelSubjectService.deactivateSubject(id);
    }
}
