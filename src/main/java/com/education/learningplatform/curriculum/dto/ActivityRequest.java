package com.education.learningplatform.curriculum.dto;

import com.education.learningplatform.curriculum.models.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ActivityRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String title;

    private String description;

    private String content;

    @NotNull(message = "{validation.notNull}")
    private ActivityType type;

    @NotNull(message = "{validation.notNull}")
    private Integer activityOrder;

    @NotNull(message = "{validation.notNull}")
    private Long lessonId;
}
