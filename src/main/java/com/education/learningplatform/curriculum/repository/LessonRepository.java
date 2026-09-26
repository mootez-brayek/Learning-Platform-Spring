package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    boolean existsByTitleAndLearningObjectiveId(
        String title,
        Long learningObjectiveId
    );

    List<Lesson> findByLearningObjectiveId(
        Long learningObjectiveId
    );
}
