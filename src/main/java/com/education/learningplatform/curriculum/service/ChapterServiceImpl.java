package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.ChapterRequest;
import com.education.learningplatform.curriculum.dto.ChapterResponse;
import com.education.learningplatform.curriculum.models.Chapter;
import com.education.learningplatform.curriculum.models.LevelSubject;
import com.education.learningplatform.curriculum.repository.ChapterRepository;
import com.education.learningplatform.curriculum.repository.LevelSubjectRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService{

    private final ChapterRepository chapterRepository;
    private final LevelSubjectRepository levelSubjectRepository;

    @Override
    public ChapterResponse createChapter(ChapterRequest request) {

        LevelSubject levelSubject =
            levelSubjectRepository.findById(request.getLevelSubjectId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "levelSubject.notFound"
                    )
                );

        List<Chapter> existingChapters =
            chapterRepository.findByLevelSubjectId(
                request.getLevelSubjectId()
            );

        for (Chapter chapter : existingChapters) {

            if (chapter.getName().equalsIgnoreCase(request.getName())) {

                if (chapter.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "chapter.alreadyExists"
                    );
                }

                chapter.setActive(true);
                chapter.setDescription(request.getDescription());
                chapter.setIcon(request.getIcon());

                Chapter reactivated =
                    chapterRepository.save(chapter);

                return toResponse(reactivated);
            }
        }

        Chapter chapter = Chapter.builder()
            .name(request.getName())
            .description(request.getDescription())
            .icon(request.getIcon())
            .levelSubject(levelSubject)
            .active(true)
            .build();

        Chapter savedChapter =
            chapterRepository.save(chapter);

        return toResponse(savedChapter);
    }

    @Override
    public ChapterResponse getChapterById(Long id) {
        Chapter chapter =
            chapterRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "chapter.notFound"
                    )
                );

        return toResponse(chapter);
    }

    @Override
    public List<ChapterResponse> getChaptersByLevelSubject(Long levelSubjectId) {
        return chapterRepository
            .findByLevelSubjectId(levelSubjectId)
            .stream()
            .filter(Chapter::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateChapter(Long id) {
        Chapter chapter =
            chapterRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "chapter.notFound"
                    )
                );

        chapter.setActive(false);

        chapterRepository.save(chapter);
    }

    private ChapterResponse toResponse(Chapter chapter) {

        return new ChapterResponse(
            chapter.getId(),
            chapter.getName(),
            chapter.getDescription(),
            chapter.getIcon(),
            chapter.getLevelSubject().getId(),
            chapter.getLevelSubject().getSubject().getName(),
            chapter.isActive()
        );
    }
}
