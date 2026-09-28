package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.AssessmentRequest;
import com.education.learningplatform.curriculum.dto.AssessmentResponse;
import com.education.learningplatform.curriculum.service.AssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssessmentResponse createAssessment(
        @Valid @RequestBody AssessmentRequest request
    ) {
        return assessmentService.createAssessment(request);
    }

    @GetMapping("/{id}")
    public AssessmentResponse getAssessmentById(
        @PathVariable Long id
    ) {
        return assessmentService.getAssessmentById(id);
    }

    @GetMapping("/learning-objective/{learningObjectiveId}")
    public List<AssessmentResponse> getAssessmentsByLearningObjective(
        @PathVariable Long learningObjectiveId
    ) {
        return assessmentService.getAssessmentsByLearningObjective(
            learningObjectiveId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateAssessment(
        @PathVariable Long id
    ) {
        assessmentService.deactivateAssessment(id);
    }
}
