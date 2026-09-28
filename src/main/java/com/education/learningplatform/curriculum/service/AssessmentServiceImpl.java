package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.AssessmentRequest;
import com.education.learningplatform.curriculum.dto.AssessmentResponse;
import com.education.learningplatform.curriculum.models.Assessment;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.repository.AssessmentRepository;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService{

    private final AssessmentRepository assessmentRepository;
    private final LearningObjectiveRepository objectiveRepository;

    @Override
    public AssessmentResponse createAssessment(AssessmentRequest request) {
        LearningObjective objective =
            objectiveRepository.findById(
                request.getLearningObjectiveId()
            ).orElseThrow(() ->
                new ResourceNotFoundException(
                    "learningObjective.notFound"
                )
            );

        List<Assessment> existingAssessments =
            assessmentRepository.findByLearningObjectiveId(
                request.getLearningObjectiveId()
            );

        for (Assessment assessment : existingAssessments) {

            if (assessment.getTitle()
                .equalsIgnoreCase(request.getTitle())) {

                if (assessment.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "assessment.alreadyExists"
                    );
                }

                assessment.setActive(true);
                assessment.setDescription(
                    request.getDescription()
                );
                assessment.setType(request.getType());
                assessment.setPassingScore(
                    request.getPassingScore()
                );

                Assessment reactivated =
                    assessmentRepository.save(assessment);

                return toResponse(reactivated);
            }
        }

        Assessment assessment = Assessment.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .type(request.getType())
            .passingScore(request.getPassingScore())
            .learningObjective(objective)
            .active(true)
            .build();

        Assessment savedAssessment =
            assessmentRepository.save(assessment);

        return toResponse(savedAssessment);
    }

    @Override
    public AssessmentResponse getAssessmentById(Long id) {
        Assessment assessment =
            assessmentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "assessment.notFound"
                    )
                );

        return toResponse(assessment);
    }

    @Override
    public List<AssessmentResponse> getAssessmentsByLearningObjective(Long learningObjectiveId) {
        return assessmentRepository
            .findByLearningObjectiveId(learningObjectiveId)
            .stream()
            .filter(Assessment::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateAssessment(Long id) {
        Assessment assessment =
            assessmentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "assessment.notFound"
                    )
                );

        assessment.setActive(false);

        assessmentRepository.save(assessment);
    }

    private AssessmentResponse toResponse(
        Assessment assessment
    ) {

        return new AssessmentResponse(
            assessment.getId(),
            assessment.getTitle(),
            assessment.getDescription(),
            assessment.getType(),
            assessment.getPassingScore(),
            assessment.getLearningObjective().getId(),
            assessment.getLearningObjective().getName(),
            assessment.isActive()
        );
    }
}
