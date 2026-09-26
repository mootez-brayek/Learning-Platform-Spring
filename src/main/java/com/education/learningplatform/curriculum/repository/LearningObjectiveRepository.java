package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.LearningObjective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningObjectiveRepository  extends JpaRepository<LearningObjective, Long> {


    boolean existsByNameAndCompetencyId(
        String name,
        Long competencyId
    );

    List<LearningObjective> findByCompetencyId(
        Long competencyId
    );
}
