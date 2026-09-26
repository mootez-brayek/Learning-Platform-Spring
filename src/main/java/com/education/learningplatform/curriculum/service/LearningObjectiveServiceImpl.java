package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.LearningObjectiveRequest;
import com.education.learningplatform.curriculum.dto.LearningObjectiveResponse;
import com.education.learningplatform.curriculum.models.Competency;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.repository.CompetencyRepository;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningObjectiveServiceImpl implements LearningObjectiveService{

    private final LearningObjectiveRepository objectiveRepository;
    private final CompetencyRepository competencyRepository;

    @Override
    public LearningObjectiveResponse createObjective(LearningObjectiveRequest request) {
        Competency competency =
            competencyRepository.findById(request.getCompetencyId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "competency.notFound"
                    )
                );

        List<LearningObjective> existingObjectives =
            objectiveRepository.findByCompetencyId(
                request.getCompetencyId()
            );

        for (LearningObjective objective : existingObjectives) {

            if (objective.getName()
                .equalsIgnoreCase(request.getName())) {

                if (objective.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "learningObjective.alreadyExists"
                    );
                }

                objective.setActive(true);
                objective.setDescription(
                    request.getDescription()
                );

                LearningObjective reactivated =
                    objectiveRepository.save(objective);

                return toResponse(reactivated);
            }
        }

        LearningObjective objective =
            LearningObjective.builder()
                .name(request.getName())
                .description(request.getDescription())
                .competency(competency)
                .active(true)
                .build();

        LearningObjective saved =
            objectiveRepository.save(objective);

        return toResponse(saved);
    }


    @Override
    public LearningObjectiveResponse getObjectiveById(Long id) {
        LearningObjective objective =
            objectiveRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "learningObjective.notFound"
                    )
                );

        return toResponse(objective);
    }

    @Override
    public List<LearningObjectiveResponse> getObjectivesByCompetency(Long competencyId) {
        return objectiveRepository
            .findByCompetencyId(competencyId)
            .stream()
            .filter(LearningObjective::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateObjective(Long id) {
        LearningObjective objective =
            objectiveRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "learningObjective.notFound"
                    )
                );

        objective.setActive(false);

        objectiveRepository.save(objective);
    }

    private LearningObjectiveResponse toResponse(
        LearningObjective objective
    ) {

        return new LearningObjectiveResponse(
            objective.getId(),
            objective.getName(),
            objective.getDescription(),
            objective.getCompetency().getId(),
            objective.getCompetency().getName(),
            objective.isActive()
        );
    }
}
