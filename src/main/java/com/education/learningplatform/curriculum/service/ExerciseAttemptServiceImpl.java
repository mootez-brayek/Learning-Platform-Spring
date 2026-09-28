package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.Security.JwtUtil;
import com.education.learningplatform.User.model.User;
import com.education.learningplatform.curriculum.dto.ExerciseAttemptRequest;
import com.education.learningplatform.curriculum.dto.ExerciseAttemptResponse;
import com.education.learningplatform.curriculum.models.Exercise;
import com.education.learningplatform.curriculum.models.ExerciseAttempt;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.models.StudentProgress;
import com.education.learningplatform.curriculum.repository.ExerciseAttemptRepository;
import com.education.learningplatform.curriculum.repository.ExerciseRepository;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.curriculum.repository.StudentProgressRepository;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseAttemptServiceImpl implements ExerciseAttemptService{

    private final ExerciseAttemptRepository exerciseAttemptRepository;
    private final ExerciseRepository exerciseRepository;
    private final LearningObjectiveRepository learningObjectiveRepository;
    private final StudentProgressRepository studentProgressRepository;

    @Override
    @Transactional
    public ExerciseAttemptResponse submitAttempt(ExerciseAttemptRequest request) {
        User currentUser = JwtUtil.getCurrentUser();

        Exercise exercise =
            exerciseRepository.findById(request.getExerciseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "exercise.notFound"
                    )
                );

        LearningObjective learningObjective =
            exercise.getLearningObjective();

        boolean correct = checkAnswer(
            exercise,
            request.getAnswer()
        );

        ExerciseAttempt attempt =
            ExerciseAttempt.builder()
                .student(currentUser)
                .exercise(exercise)
                .learningObjective(learningObjective)
                .correct(correct)
                .attemptedAt(LocalDateTime.now())
                .build();

        ExerciseAttempt savedAttempt =
            exerciseAttemptRepository.save(attempt);

        updateProgress(
            currentUser,
            learningObjective,
            correct
        );

        return toResponse(savedAttempt);
    }

    @Override
    public List<ExerciseAttemptResponse> getMyAttempts() {
        User currentUser = JwtUtil.getCurrentUser();

        return exerciseAttemptRepository
            .findByStudentId(currentUser.getId())
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public List<ExerciseAttemptResponse> getMyAttemptsByObjective(Long learningObjectiveId) {
        User currentUser = JwtUtil.getCurrentUser();

        learningObjectiveRepository
            .findById(learningObjectiveId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "learningObjective.notFound"
                )
            );

        return exerciseAttemptRepository
            .findByStudentIdAndLearningObjectiveId(
                currentUser.getId(),
                learningObjectiveId
            )
            .stream()
            .map(this::toResponse)
            .toList();
    }

    private void updateProgress(
        User student,
        LearningObjective learningObjective,
        boolean correct
    ) {

        StudentProgress progress =
            studentProgressRepository
                .findByStudentIdAndLearningObjectiveId(
                    student.getId(),
                    learningObjective.getId()
                )
                .orElseGet(() ->
                    StudentProgress.builder()
                        .student(student)
                        .learningObjective(learningObjective)
                        .exercisesAttempted(0)
                        .exercisesCorrect(0)
                        .masteryPercentage(0.0)
                        .build()
                );

        progress.setExercisesAttempted(
            progress.getExercisesAttempted() + 1
        );

        if (correct) {
            progress.setExercisesCorrect(
                progress.getExercisesCorrect() + 1
            );
        }

        double mastery =
            ((double) progress.getExercisesCorrect()
                / progress.getExercisesAttempted()) * 100;

        progress.setMasteryPercentage(mastery);
        progress.setLastActivityAt(LocalDateTime.now());

        studentProgressRepository.save(progress);
    }

    private boolean checkAnswer(
        Exercise exercise,
        String answer
    ) {

        /*
         * TODO:
         * The correction logic will depend on the generated
         * exercise/question structure.
         *
         * Examples:
         * QCM          -> compare selected option
         * TRUE_FALSE   -> compare boolean answer
         * ENTER_ANSWER -> compare expected answer
         * COMPLETE     -> compare missing value
         * MATCHING     -> compare pairs
         * ORDERING     -> compare order
         * COMPARE      -> compare mathematical result
         */

        return false;
    }

    private ExerciseAttemptResponse toResponse(
        ExerciseAttempt attempt
    ) {

        return new ExerciseAttemptResponse(
            attempt.getId(),

            attempt.getExercise().getId(),
            attempt.getExercise().getTitle(),

            attempt.getLearningObjective().getId(),
            attempt.getLearningObjective().getName(),

            attempt.isCorrect(),
            attempt.getAttemptedAt()
        );
    }
}
