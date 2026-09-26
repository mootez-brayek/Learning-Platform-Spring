package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Competency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetencyRepository extends JpaRepository<Competency, Long> {

    boolean existsByNameAndChapterId(
        String name,
        Long chapterId
    );

    List<Competency> findByChapterId(Long chapterId);
}
