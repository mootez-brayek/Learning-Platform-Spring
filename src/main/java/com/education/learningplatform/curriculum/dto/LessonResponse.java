package com.education.learningplatform.curriculum.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LessonResponse {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String icon;
    private Integer duration;
    private Integer lessonOrder;
    private Long learningObjectiveId;
    private String learningObjectiveName;
    private boolean active;
}
