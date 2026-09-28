package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.ExerciseAttemptRequest;
import com.education.learningplatform.curriculum.dto.ExerciseAttemptResponse;
import com.education.learningplatform.curriculum.service.ExerciseAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-attempts")
@RequiredArgsConstructor
public class ExerciseAttemptController {

    private final ExerciseAttemptService exerciseAttemptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseAttemptResponse submitAttempt(
        @Valid @RequestBody ExerciseAttemptRequest request
    ) {
        return exerciseAttemptService.submitAttempt(request);
    }

    @GetMapping
    public List<ExerciseAttemptResponse> getMyAttempts() {
        return exerciseAttemptService.getMyAttempts();
    }

    @GetMapping("/objective/{learningObjectiveId}")
    public List<ExerciseAttemptResponse> getMyAttemptsByObjective(
        @PathVariable Long learningObjectiveId
    ) {
        return exerciseAttemptService.getMyAttemptsByObjective(
            learningObjectiveId
        );
    }
}
