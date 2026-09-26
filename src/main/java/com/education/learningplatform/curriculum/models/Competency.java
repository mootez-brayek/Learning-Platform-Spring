package com.education.learningplatform.curriculum.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "competencies",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"name", "chapter_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;
}
