package com.education.learningplatform.curriculum.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
    (
    name = "lessons",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"title", "learning_objective_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String icon;

    private Integer duration;

    @Column(nullable = false)
    private Integer lessonOrder;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "learning_objective_id", nullable = false)
    private LearningObjective learningObjective;
}
