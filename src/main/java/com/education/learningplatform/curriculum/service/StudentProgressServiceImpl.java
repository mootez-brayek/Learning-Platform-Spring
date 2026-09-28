package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.Security.JwtUtil;
import com.education.learningplatform.User.model.User;
import com.education.learningplatform.curriculum.dto.StudentProgressRequest;
import com.education.learningplatform.curriculum.dto.StudentProgressResponse;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.models.StudentProgress;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.curriculum.repository.StudentProgressRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentProgressServiceImpl implements StudentProgressService{

    private final StudentProgressRepository studentProgressRepository;
    private final LearningObjectiveRepository learningObjectiveRepository;

    @Override
    public StudentProgressResponse getMyProgressByObjective(Long learningObjectiveId) {
        User currentUser = JwtUtil.getCurrentUser();

        StudentProgress progress =
            studentProgressRepository
                .findByStudentIdAndLearningObjectiveId(
                    currentUser.getId(),
                    learningObjectiveId
                )
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "studentProgress.notFound"
                    )
                );

        return toResponse(progress);
    }

    @Override
    public List<StudentProgressResponse> getMyProgress() {
        User currentUser = JwtUtil.getCurrentUser();

        return studentProgressRepository
            .findByStudentId(currentUser.getId())
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public StudentProgressResponse initializeProgress(StudentProgressRequest request) {
        User currentUser = JwtUtil.getCurrentUser();

        LearningObjective learningObjective =
            learningObjectiveRepository
                .findById(request.getLearningObjectiveId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "learningObjective.notFound"
                    )
                );

        if (studentProgressRepository
            .findByStudentIdAndLearningObjectiveId(
                currentUser.getId(),
                learningObjective.getId()
            )
            .isPresent()) {

            throw new ResourceAlreadyExistsException(
                "studentProgress.alreadyExists"
            );
        }

        StudentProgress progress =
            StudentProgress.builder()
                .student(currentUser)
                .learningObjective(learningObjective)
                .exercisesAttempted(0)
                .exercisesCorrect(0)
                .masteryPercentage(0.0)
                .lastActivityAt(null)
                .build();

        StudentProgress saved =
            studentProgressRepository.save(progress);

        return toResponse(saved);
    }

    private StudentProgressResponse toResponse(
        StudentProgress progress
    ) {

        return new StudentProgressResponse(
            progress.getId(),
            progress.getStudent().getId(),
            progress.getLearningObjective().getId(),
            progress.getLearningObjective().getName(),
            progress.getExercisesAttempted(),
            progress.getExercisesCorrect(),
            progress.getMasteryPercentage(),
            progress.getLastActivityAt()
        );
    }
}
