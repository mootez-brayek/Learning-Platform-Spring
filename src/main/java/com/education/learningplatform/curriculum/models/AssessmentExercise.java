package com.education.learningplatform.curriculum.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "assessment_exercises",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"assessment_id", "exercise_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer exerciseOrder;

    @Column(nullable = false)
    private Integer points;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
