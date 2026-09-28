package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentProgressRepository  extends JpaRepository<StudentProgress, Long> {

    Optional<StudentProgress> findByStudentIdAndLearningObjectiveId(
        Long studentId,
        Long learningObjectiveId
    );

    List<StudentProgress> findByStudentId(Long studentId);
}
