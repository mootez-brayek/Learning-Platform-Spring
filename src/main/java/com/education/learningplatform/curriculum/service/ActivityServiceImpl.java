package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ActivityRequest;
import com.education.learningplatform.curriculum.dto.ActivityResponse;
import com.education.learningplatform.curriculum.models.Activity;
import com.education.learningplatform.curriculum.models.Lesson;
import com.education.learningplatform.curriculum.repository.ActivityRepository;
import com.education.learningplatform.curriculum.repository.LessonRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final LessonRepository lessonRepository;

    @Override
    public ActivityResponse createActivity(ActivityRequest request) {
        Lesson lesson =
            lessonRepository.findById(request.getLessonId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "lesson.notFound"
                    )
                );

        List<Activity> existingActivities =
            activityRepository.findByLessonId(
                request.getLessonId()
            );

        for (Activity activity : existingActivities) {

            if (activity.getTitle()
                .equalsIgnoreCase(request.getTitle())) {

                if (activity.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "activity.alreadyExists"
                    );
                }

                activity.setActive(true);
                activity.setDescription(request.getDescription());
                activity.setContent(request.getContent());
                activity.setType(request.getType());
                activity.setActivityOrder(
                    request.getActivityOrder()
                );

                Activity reactivated =
                    activityRepository.save(activity);

                return toResponse(reactivated);
            }
        }

        Activity activity = Activity.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .content(request.getContent())
            .type(request.getType())
            .activityOrder(request.getActivityOrder())
            .lesson(lesson)
            .active(true)
            .build();

        Activity savedActivity =
            activityRepository.save(activity);

        return toResponse(savedActivity);    }

    @Override
    public ActivityResponse getActivityById(Long id) {
        Activity activity =
            activityRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "activity.notFound"
                    )
                );

        return toResponse(activity);
    }

    @Override
    public List<ActivityResponse> getActivitiesByLesson(Long lessonId) {
        return activityRepository
            .findByLessonId(lessonId)
            .stream()
            .filter(Activity::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateActivity(Long id) {
        Activity activity =
            activityRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "activity.notFound"
                    )
                );

        activity.setActive(false);

        activityRepository.save(activity);
    }

    private ActivityResponse toResponse(Activity activity) {

        return new ActivityResponse(
            activity.getId(),
            activity.getTitle(),
            activity.getDescription(),
            activity.getContent(),
            activity.getType(),
            activity.getActivityOrder(),
            activity.getLesson().getId(),
            activity.getLesson().getTitle(),
            activity.isActive()
        );
    }
}
