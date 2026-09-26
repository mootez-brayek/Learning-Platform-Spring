package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.User.service.CompetencyService;
import com.education.learningplatform.curriculum.dto.CompetencyRequest;
import com.education.learningplatform.curriculum.dto.CompetencyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competencies")
@RequiredArgsConstructor
public class CompetencyController {
    private final CompetencyService competencyService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompetencyResponse createCompetency(
        @Valid @RequestBody CompetencyRequest request
    ) {
        return competencyService.createCompetency(request);
    }

    @GetMapping("/{id}")
    public CompetencyResponse getCompetencyById(
        @PathVariable Long id
    ) {
        return competencyService.getCompetencyById(id);
    }

    @GetMapping("/chapter/{chapterId}")
    public List<CompetencyResponse> getCompetenciesByChapter(
        @PathVariable Long chapterId
    ) {
        return competencyService.getCompetenciesByChapter(chapterId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateCompetency(
        @PathVariable Long id
    ) {
        competencyService.deactivateCompetency(id);
    }

}
