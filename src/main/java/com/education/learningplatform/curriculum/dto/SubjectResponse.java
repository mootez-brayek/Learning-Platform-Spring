package com.education.learningplatform.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectResponse {

    private Long id;
    private String name;
    private String description;
    private String icon;
    private boolean active;
}
