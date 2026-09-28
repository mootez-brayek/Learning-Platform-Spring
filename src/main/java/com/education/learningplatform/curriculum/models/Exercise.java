package com.education.learningplatform.curriculum.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String instruction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType type;

    @Column(nullable = false)
    private Integer difficulty;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "learning_objective_id", nullable = false)
    private LearningObjective learningObjective;
}
