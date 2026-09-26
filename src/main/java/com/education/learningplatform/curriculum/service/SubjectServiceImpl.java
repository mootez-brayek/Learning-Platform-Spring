package com.education.learningplatform.curriculum.service;

import com.education.learningplatform.curriculum.dto.SubjectRequest;
import com.education.learningplatform.curriculum.dto.SubjectResponse;
import com.education.learningplatform.curriculum.models.Subject;
import com.education.learningplatform.curriculum.repository.SubjectRepository;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectResponse createSubject(SubjectRequest request) {
        if (subjectRepository.existsByName(request.getName())) {
            throw new ResourceAlreadyExistsException("subject.alreadyExists");
        }

        Subject subject = Subject.builder()
            .name(request.getName())
            .description(request.getDescription())
            .icon(request.getIcon())
            .active(true)
            .build();

        Subject savedSubject = subjectRepository.save(subject);

        return toResponse(savedSubject);
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("subject.notFound")
            );

        return toResponse(subject);
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectRequest request) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("subject.notFound"));

        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        subject.setIcon(request.getIcon());

        Subject updatedSubject = subjectRepository.save(subject);

        return toResponse(updatedSubject);
    }

    @Override public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("subject.notFound")
            );
        subjectRepository.delete(subject);
    }

    private SubjectResponse toResponse(Subject subject) {

        return new SubjectResponse(
            subject.getId(),
            subject.getName(),
            subject.getDescription(),
            subject.getIcon(),
            subject.isActive()
        );
    }
}
