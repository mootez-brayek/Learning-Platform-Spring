package com.education.learningplatform.curriculum.models;

import com.education.learningplatform.User.model.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "level_subjects",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"education_level", "subject_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_level", nullable = false)
    private EducationLevel educationLevel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private boolean active = true;
}
