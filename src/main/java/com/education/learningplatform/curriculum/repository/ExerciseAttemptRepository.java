package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.ExerciseAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseAttemptRepository extends JpaRepository<ExerciseAttempt, Long> {

    List<ExerciseAttempt> findByStudentId(Long studentId);

    List<ExerciseAttempt> findByStudentIdAndLearningObjectiveId(
        Long studentId,
        Long learningObjectiveId
    );

    List<ExerciseAttempt> findByStudentIdAndExerciseId(
        Long studentId,
        Long exerciseId
    );
}
