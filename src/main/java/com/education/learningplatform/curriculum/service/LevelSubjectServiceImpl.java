package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.User.model.EducationLevel;
import com.education.learningplatform.curriculum.dto.LevelSubjectRequest;
import com.education.learningplatform.curriculum.dto.LevelSubjectResponse;
import com.education.learningplatform.curriculum.models.LevelSubject;
import com.education.learningplatform.curriculum.models.Subject;
import com.education.learningplatform.curriculum.repository.LevelSubjectRepository;
import com.education.learningplatform.curriculum.repository.SubjectRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelSubjectServiceImpl implements LevelSubjectService{

    private final LevelSubjectRepository levelSubjectRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public LevelSubjectResponse assignSubject(LevelSubjectRequest request) {
        Subject subject = subjectRepository.findById(request.getSubjectId())
            .orElseThrow(() ->
                new ResourceNotFoundException("subject.notFound")
            );

        List<LevelSubject> existingAssociations =
            levelSubjectRepository.findByEducationLevel(
                request.getEducationLevel()
            );

        for (LevelSubject levelSubject : existingAssociations) {

            if (levelSubject.getSubject().getId()
                .equals(request.getSubjectId())) {

                if (levelSubject.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "levelSubject.alreadyExists"
                    );
                }

                levelSubject.setActive(true);

                LevelSubject reactivated =
                    levelSubjectRepository.save(levelSubject);

                return toResponse(reactivated);
            }
        }

        LevelSubject levelSubject = LevelSubject.builder()
            .educationLevel(request.getEducationLevel())
            .subject(subject)
            .active(true)
            .build();

        LevelSubject saved =
            levelSubjectRepository.save(levelSubject);

        return toResponse(saved);
    }

    @Override
    public List<LevelSubjectResponse> getSubjectsByLevel(EducationLevel educationLevel) {
        return levelSubjectRepository
            .findByEducationLevel(educationLevel)
            .stream()
            .filter(LevelSubject::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateSubject(Long id) {
        LevelSubject levelSubject =
            levelSubjectRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "levelSubject.notFound"
                    )
                );

        levelSubject.setActive(false);

        levelSubjectRepository.save(levelSubject);
    }

    private LevelSubjectResponse toResponse(
        LevelSubject levelSubject
    ) {

        return new LevelSubjectResponse(
            levelSubject.getId(),
            levelSubject.getEducationLevel(),
            levelSubject.getSubject().getId(),
            levelSubject.getSubject().getName(),
            levelSubject.isActive()
        );
    }
}
