package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ActivityRequest;
import com.education.learningplatform.curriculum.dto.ActivityResponse;

import java.util.List;

public interface ActivityService {

    ActivityResponse createActivity(ActivityRequest request);

    ActivityResponse getActivityById(Long id);

    List<ActivityResponse> getActivitiesByLesson(Long lessonId);

    void deactivateActivity(Long id);
}
