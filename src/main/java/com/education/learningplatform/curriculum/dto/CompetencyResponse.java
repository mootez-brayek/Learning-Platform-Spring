package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompetencyResponse {

    private Long id;
    private String name;
    private String description;
    private Long chapterId;
    private String chapterName;
    private boolean active;
}
