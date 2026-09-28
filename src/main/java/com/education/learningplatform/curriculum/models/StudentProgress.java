package com.education.learningplatform.curriculum.models;


import com.education.learningplatform.User.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "student_progress",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"student_id", "learning_objective_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer exercisesAttempted = 0;

    @Column(nullable = false)
    private Integer exercisesCorrect = 0;

    @Column(nullable = false)
    private Double masteryPercentage = 0.0;

    private LocalDateTime lastActivityAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "learning_objective_id", nullable = false)
    private LearningObjective learningObjective;
}
