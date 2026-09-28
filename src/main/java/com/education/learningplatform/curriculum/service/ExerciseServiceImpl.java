package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ExerciseRequest;
import com.education.learningplatform.curriculum.dto.ExerciseResponse;
import com.education.learningplatform.curriculum.models.Exercise;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.repository.ExerciseRepository;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;
    private final LearningObjectiveRepository objectiveRepository;
    @Override
    public ExerciseResponse createExercise(ExerciseRequest request) {
        LearningObjective objective =
            objectiveRepository.findById(
                request.getLearningObjectiveId()
            ).orElseThrow(() ->
                new ResourceNotFoundException(
                    "learningObjective.notFound"
                )
            );

        List<Exercise> existingExercises =
            exerciseRepository.findByLearningObjectiveId(
                request.getLearningObjectiveId()
            );

        for (Exercise exercise : existingExercises) {

            if (exercise.getTitle()
                .equalsIgnoreCase(request.getTitle())) {

                if (exercise.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "exercise.alreadyExists"
                    );
                }

                exercise.setActive(true);
                exercise.setInstruction(request.getInstruction());
                exercise.setType(request.getType());
                exercise.setDifficulty(request.getDifficulty());

                Exercise reactivated =
                    exerciseRepository.save(exercise);

                return toResponse(reactivated);
            }
        }

        Exercise exercise = Exercise.builder()
            .title(request.getTitle())
            .instruction(request.getInstruction())
            .type(request.getType())
            .difficulty(request.getDifficulty())
            .learningObjective(objective)
            .active(true)
            .build();

        Exercise savedExercise =
            exerciseRepository.save(exercise);

        return toResponse(savedExercise);
    }

    @Override
    public ExerciseResponse getExerciseById(Long id) {
        Exercise exercise =
            exerciseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "exercise.notFound"
                    )
                );

        return toResponse(exercise);
    }

    @Override
    public List<ExerciseResponse> getExercisesByLearningObjective(Long learningObjectiveId) {
        return exerciseRepository
            .findByLearningObjectiveId(learningObjectiveId)
            .stream()
            .filter(Exercise::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateExercise(Long id) {

        Exercise exercise =
            exerciseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "exercise.notFound"
                    )
                );

        exercise.setActive(false);

        exerciseRepository.save(exercise);
    }

    private ExerciseResponse toResponse(Exercise exercise) {

        return new ExerciseResponse(
            exercise.getId(),
            exercise.getTitle(),
            exercise.getInstruction(),
            exercise.getType(),
            exercise.getDifficulty(),
            exercise.getLearningObjective().getId(),
            exercise.getLearningObjective().getName(),
            exercise.isActive()
        );
    }
}
