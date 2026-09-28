package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivityResponse {

    private Long id;
    private String title;
    private String description;
    private String content;
    private ActivityType type;
    private Integer activityOrder;
    private Long lessonId;
    private String lessonTitle;
    private boolean active;
}
