package com.education.learningplatform.curriculum.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "activities",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"title", "lesson_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType type;

    @Column(nullable = false)
    private Integer activityOrder;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}
