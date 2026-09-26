package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.LearningObjectiveRequest;
import com.education.learningplatform.curriculum.dto.LearningObjectiveResponse;
import com.education.learningplatform.curriculum.service.LearningObjectiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-objectives")
@RequiredArgsConstructor
public class LearningObjectiveController {

    private final LearningObjectiveService learningObjectiveService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LearningObjectiveResponse createObjective(
        @Valid @RequestBody LearningObjectiveRequest request
    ) {
        return learningObjectiveService.createObjective(request);
    }

    @GetMapping("/{id}")
    public LearningObjectiveResponse getObjectiveById(
        @PathVariable Long id
    ) {
        return learningObjectiveService.getObjectiveById(id);
    }

    @GetMapping("/competency/{competencyId}")
    public List<LearningObjectiveResponse> getObjectivesByCompetency(
        @PathVariable Long competencyId
    ) {
        return learningObjectiveService.getObjectivesByCompetency(
            competencyId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateObjective(
        @PathVariable Long id
    ) {
        learningObjectiveService.deactivateObjective(id);
    }
}
