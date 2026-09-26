package com.education.learningplatform.curriculum.repository;

import com.education.learningplatform.curriculum.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsByName(String name);
}
