package com.education.learningplatform.curriculum.controller;

import com.education.learningplatform.curriculum.dto.SubjectRequest;
import com.education.learningplatform.curriculum.dto.SubjectResponse;
import com.education.learningplatform.curriculum.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponse createSubject(
        @Valid @RequestBody SubjectRequest request
    ) {
        System.out.println("CREATE SUBJECT METHOD REACHED");
        return subjectService.createSubject(request);
    }


    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(
        @PathVariable Long id
    ) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping
    public List<SubjectResponse> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
        @PathVariable Long id,
        @Valid @RequestBody SubjectRequest request
    ) {
        return subjectService.updateSubject(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(
        @PathVariable Long id
    ) {
        subjectService.deleteSubject(id);
    }
}
