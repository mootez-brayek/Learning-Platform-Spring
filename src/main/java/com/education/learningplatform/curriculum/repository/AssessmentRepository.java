package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    boolean existsByTitleAndLearningObjectiveId(
        String title,
        Long learningObjectiveId
    );

    List<Assessment> findByLearningObjectiveId(
        Long learningObjectiveId
    );
}
