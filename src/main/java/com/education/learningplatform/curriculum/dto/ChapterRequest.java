package com.education.learningplatform.curriculum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChapterRequest {

    @NotBlank(message = "{validation.notBlank}")
    private String name;

    private String description;

    private String icon;

    @NotNull(message = "{validation.notNull}")
    private Long levelSubjectId;
}
