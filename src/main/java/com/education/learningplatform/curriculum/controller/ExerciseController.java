package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.ExerciseRequest;
import com.education.learningplatform.curriculum.dto.ExerciseResponse;
import com.education.learningplatform.curriculum.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(
        @Valid @RequestBody ExerciseRequest request
    ) {
        return exerciseService.createExercise(request);
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(
        @PathVariable Long id
    ) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping("/learning-objective/{learningObjectiveId}")
    public List<ExerciseResponse> getExercisesByLearningObjective(
        @PathVariable Long learningObjectiveId
    ) {
        return exerciseService.getExercisesByLearningObjective(
            learningObjectiveId
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateExercise(
        @PathVariable Long id
    ) {
        exerciseService.deactivateExercise(id);
    }
}
