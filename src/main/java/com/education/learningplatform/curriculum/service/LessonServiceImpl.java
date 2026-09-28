package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.LessonRequest;
import com.education.learningplatform.curriculum.dto.LessonResponse;
import com.education.learningplatform.curriculum.models.LearningObjective;
import com.education.learningplatform.curriculum.models.Lesson;
import com.education.learningplatform.curriculum.repository.LearningObjectiveRepository;
import com.education.learningplatform.curriculum.repository.LessonRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;
    private final LearningObjectiveRepository objectiveRepository;

    @Override
    public LessonResponse createLesson(LessonRequest request) {

        LearningObjective objective =
            objectiveRepository.findById(
                request.getLearningObjectiveId()
            ).orElseThrow(() ->
                new ResourceNotFoundException(
                    "learningObjective.notFound"
                )
            );

        List<Lesson> existingLessons =
            lessonRepository.findByLearningObjectiveId(
                request.getLearningObjectiveId()
            );

        for (Lesson lesson : existingLessons) {

            if (lesson.getTitle()
                .equalsIgnoreCase(request.getTitle())) {

                if (lesson.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "lesson.alreadyExists"
                    );
                }

                lesson.setActive(true);
                lesson.setDescription(request.getDescription());
                lesson.setContent(request.getContent());
                lesson.setIcon(request.getIcon());
                lesson.setDuration(request.getDuration());
                lesson.setLessonOrder(request.getLessonOrder());

                Lesson reactivated =
                    lessonRepository.save(lesson);

                return toResponse(reactivated);
            }
        }

        Lesson lesson = Lesson.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .content(request.getContent())
            .icon(request.getIcon())
            .duration(request.getDuration())
            .lessonOrder(request.getLessonOrder())
            .learningObjective(objective)
            .active(true)
            .build();

        Lesson savedLesson =
            lessonRepository.save(lesson);

        return toResponse(savedLesson);
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson =
            lessonRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "lesson.notFound"
                    )
                );

        return toResponse(lesson);
    }

    @Override
    public List<LessonResponse> getLessonsByLearningObjective(Long learningObjectiveId) {
        return lessonRepository
            .findByLearningObjectiveId(learningObjectiveId)
            .stream()
            .filter(Lesson::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateLesson(Long id) {
        Lesson lesson =
            lessonRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "lesson.notFound"
                    )
                );

        lesson.setActive(false);

        lessonRepository.save(lesson);
    }

    private LessonResponse toResponse(Lesson lesson) {

        return new LessonResponse(
            lesson.getId(),
            lesson.getTitle(),
            lesson.getDescription(),
            lesson.getContent(),
            lesson.getIcon(),
            lesson.getDuration(),
            lesson.getLessonOrder(),
            lesson.getLearningObjective().getId(),
            lesson.getLearningObjective().getName(),
            lesson.isActive()
        );
    }
}
