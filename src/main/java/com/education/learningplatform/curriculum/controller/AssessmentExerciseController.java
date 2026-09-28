package com.education.learningplatform.curriculum.controller;


import com.education.learningplatform.curriculum.dto.AssessmentExerciseRequest;
import com.education.learningplatform.curriculum.dto.AssessmentExerciseResponse;
import com.education.learningplatform.curriculum.service.AssessmentExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment-exercises")
@RequiredArgsConstructor
public class AssessmentExerciseController {

    private final AssessmentExerciseService assessmentExerciseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssessmentExerciseResponse addExerciseToAssessment(
        @Valid @RequestBody AssessmentExerciseRequest request
    ) {
        return assessmentExerciseService.addExerciseToAssessment(request);
    }

    @GetMapping("/assessment/{assessmentId}")
    public List<AssessmentExerciseResponse> getExercisesByAssessment(
        @PathVariable Long assessmentId
    ) {
        return assessmentExerciseService.getExercisesByAssessment(
            assessmentId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateExerciseFromAssessment(
        @PathVariable Long id
    ) {
        assessmentExerciseService.deactivateExerciseFromAssessment(id);
    }
}
