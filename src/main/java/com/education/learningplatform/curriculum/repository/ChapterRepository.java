package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    boolean existsByNameAndLevelSubjectId(
        String name,
        Long levelSubjectId
    );

    List<Chapter> findByLevelSubjectId(Long levelSubjectId);
}
