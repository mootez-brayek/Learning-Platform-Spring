package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String title;

    private String description;

    private String content;

    private String icon;

    @NotNull(message = "{validation.notNull}")
    private Integer duration;

    @NotNull(message = "{validation.notNull}")
    private Integer lessonOrder;

    @NotNull(message = "{validation.notNull}")
    private Long learningObjectiveId;
}
