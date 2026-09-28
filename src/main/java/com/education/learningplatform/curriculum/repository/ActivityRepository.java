package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    boolean existsByTitleAndLessonId(
        String title,
        Long lessonId
    );

    List<Activity> findByLessonId(
        Long lessonId
    );
}
