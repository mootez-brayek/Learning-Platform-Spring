package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.AssessmentExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentExerciseRepository extends JpaRepository<AssessmentExercise, Long> {

    List<AssessmentExercise> findByAssessmentIdOrderByExerciseOrder(
        Long assessmentId
    );

    Optional<AssessmentExercise> findByAssessmentIdAndExerciseId(
        Long assessmentId,
        Long exerciseId
    );
}
