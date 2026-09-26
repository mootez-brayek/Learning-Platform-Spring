package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.User.model.EducationLevel;
import com.education.learningplatform.curriculum.models.LevelSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LevelSubjectRepository extends JpaRepository<LevelSubject, Long> {

    boolean existsByEducationLevelAndSubjectId(
        EducationLevel educationLevel,
        Long subjectId
    );

    List<LevelSubject> findByEducationLevel(
        EducationLevel educationLevel
    );
}
