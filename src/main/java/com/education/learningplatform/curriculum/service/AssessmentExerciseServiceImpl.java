package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.AssessmentExerciseRequest;
import com.education.learningplatform.curriculum.dto.AssessmentExerciseResponse;
import com.education.learningplatform.curriculum.models.Assessment;
import com.education.learningplatform.curriculum.models.AssessmentExercise;
import com.education.learningplatform.curriculum.models.Exercise;
import com.education.learningplatform.curriculum.repository.AssessmentExerciseRepository;
import com.education.learningplatform.curriculum.repository.AssessmentRepository;
import com.education.learningplatform.curriculum.repository.ExerciseRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentExerciseServiceImpl implements AssessmentExerciseService{

    private final AssessmentExerciseRepository assessmentExerciseRepository;
    private final AssessmentRepository assessmentRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public AssessmentExerciseResponse addExerciseToAssessment(AssessmentExerciseRequest request) {
        Assessment assessment =
            assessmentRepository.findById(request.getAssessmentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "assessment.notFound"
                    )
                );

        Exercise exercise =
            exerciseRepository.findById(request.getExerciseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "exercise.notFound"
                    )
                );

        var existing =
            assessmentExerciseRepository
                .findByAssessmentIdAndExerciseId(
                    request.getAssessmentId(),
                    request.getExerciseId()
                );

        if (existing.isPresent()) {

            AssessmentExercise assessmentExercise =
                existing.get();

            if (assessmentExercise.isActive()) {
                throw new ResourceAlreadyExistsException(
                    "assessmentExercise.alreadyExists"
                );
            }

            // Reactivate existing association
            assessmentExercise.setActive(true);
            assessmentExercise.setExerciseOrder(
                request.getExerciseOrder()
            );
            assessmentExercise.setPoints(
                request.getPoints()
            );

            AssessmentExercise saved =
                assessmentExerciseRepository.save(
                    assessmentExercise
                );

            return toResponse(saved);
        }

        AssessmentExercise assessmentExercise =
            AssessmentExercise.builder()
                .assessment(assessment)
                .exercise(exercise)
                .exerciseOrder(request.getExerciseOrder())
                .points(request.getPoints())
                .active(true)
                .build();

        AssessmentExercise saved =
            assessmentExerciseRepository.save(
                assessmentExercise
            );

        return toResponse(saved);
    }

    @Override
    public List<AssessmentExerciseResponse> getExercisesByAssessment(Long assessmentId) {
        return assessmentExerciseRepository
            .findByAssessmentIdOrderByExerciseOrder(assessmentId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateExerciseFromAssessment(Long id) {
        AssessmentExercise assessmentExercise =
            assessmentExerciseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "assessmentExercise.notFound"
                    )
                );

        assessmentExercise.setActive(false);

        assessmentExerciseRepository.save(assessmentExercise);
    }

    private AssessmentExerciseResponse toResponse(
        AssessmentExercise assessmentExercise
    ) {

        return new AssessmentExerciseResponse(
            assessmentExercise.getId(),

            assessmentExercise.getAssessment().getId(),
            assessmentExercise.getAssessment().getTitle(),

            assessmentExercise.getExercise().getId(),
            assessmentExercise.getExercise().getTitle(),

            assessmentExercise.getExerciseOrder(),
            assessmentExercise.getPoints()
        );
    }
}
