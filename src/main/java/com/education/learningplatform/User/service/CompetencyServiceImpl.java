package com.education.learningplatform.User.service;

import com.education.learningplatform.curriculum.dto.CompetencyRequest;
import com.education.learningplatform.curriculum.dto.CompetencyResponse;
import com.education.learningplatform.curriculum.models.Chapter;
import com.education.learningplatform.curriculum.models.Competency;
import com.education.learningplatform.curriculum.repository.ChapterRepository;
import com.education.learningplatform.curriculum.repository.CompetencyRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetencyServiceImpl implements CompetencyService{

    private final CompetencyRepository competencyRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public CompetencyResponse createCompetency(CompetencyRequest request) {
        Chapter chapter = chapterRepository.findById(
            request.getChapterId()
        ).orElseThrow(() ->
            new ResourceNotFoundException("chapter.notFound")
        );

        List<Competency> existingCompetencies =
            competencyRepository.findByChapterId(
                request.getChapterId()
            );

        for (Competency competency : existingCompetencies) {

            if (competency.getName()
                .equalsIgnoreCase(request.getName())) {

                if (competency.isActive()) {
                    throw new ResourceAlreadyExistsException(
                        "competency.alreadyExists"
                    );
                }

                competency.setActive(true);
                competency.setDescription(
                    request.getDescription()
                );

                Competency reactivated =
                    competencyRepository.save(competency);

                return toResponse(reactivated);
            }
        }

        Competency competency = Competency.builder()
            .name(request.getName())
            .description(request.getDescription())
            .chapter(chapter)
            .active(true)
            .build();

        Competency savedCompetency =
            competencyRepository.save(competency);

        return toResponse(savedCompetency);
    }

    @Override
    public CompetencyResponse getCompetencyById(Long id) {
        Competency competency =
            competencyRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "competency.notFound"
                    )
                );

        return toResponse(competency);
    }

    @Override
    public List<CompetencyResponse> getCompetenciesByChapter(Long chapterId) {
        return competencyRepository
            .findByChapterId(chapterId)
            .stream()
            .filter(Competency::isActive)
            .map(this::toResponse)
            .toList();
    }

    @Override
    public void deactivateCompetency(Long id) {
        Competency competency =
            competencyRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "competency.notFound"
                    )
                );

        competency.setActive(false);

        competencyRepository.save(competency);
    }

    private CompetencyResponse toResponse(
        Competency competency
    ) {

        return new CompetencyResponse(
            competency.getId(),
            competency.getName(),
            competency.getDescription(),
            competency.getChapter().getId(),
            competency.getChapter().getName(),
            competency.isActive()
        );
    }
}
