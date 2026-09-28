package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.StudentProgressRequest;
import com.education.learningplatform.curriculum.dto.StudentProgressResponse;
import com.education.learningplatform.curriculum.service.StudentProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class StudentProgressController {
    private final StudentProgressService studentProgressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentProgressResponse initializeProgress(
        @Valid @RequestBody StudentProgressRequest request
    ) {
        return studentProgressService.initializeProgress(request);
    }

    @GetMapping
    public List<StudentProgressResponse> getMyProgress() {
        return studentProgressService.getMyProgress();
    }

    @GetMapping("/objective/{learningObjectiveId}")
    public StudentProgressResponse getMyProgressByObjective(
        @PathVariable Long learningObjectiveId
    ) {
        return studentProgressService.getMyProgressByObjective(
            learningObjectiveId
        );
    }
}
