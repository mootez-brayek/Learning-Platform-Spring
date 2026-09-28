package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.ActivityRequest;
import com.education.learningplatform.curriculum.dto.ActivityResponse;
import com.education.learningplatform.curriculum.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityResponse createActivity(
        @Valid @RequestBody ActivityRequest request
    ) {
        return activityService.createActivity(request);
    }

    @GetMapping("/{id}")
    public ActivityResponse getActivityById(
        @PathVariable Long id
    ) {
        return activityService.getActivityById(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<ActivityResponse> getActivitiesByLesson(
        @PathVariable Long lessonId
    ) {
        return activityService.getActivitiesByLesson(lessonId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateActivity(
        @PathVariable Long id
    ) {
        activityService.deactivateActivity(id);
    }
}
