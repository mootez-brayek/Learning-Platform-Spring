package com.education.learningplatform.curriculum.models;

import com.education.learningplatform.User.model.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String icon;

    @Column(nullable = false)
    private boolean active = true;
}
