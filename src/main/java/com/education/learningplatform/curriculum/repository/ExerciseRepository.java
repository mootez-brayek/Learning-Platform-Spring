package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    boolean existsByTitleAndLearningObjectiveId(
        String title,
        Long learningObjectiveId
    );

    List<Exercise> findByLearningObjectiveId(
        Long learningObjectiveId
    );
}
