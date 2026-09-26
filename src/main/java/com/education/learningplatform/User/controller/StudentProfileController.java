package com.education.learningplatform.User.controller;

import com.education.learningplatform.User.dto.StudentProfileResponse;
import com.education.learningplatform.User.dto.UpdateStudentProfileRequest;
import com.education.learningplatform.User.service.StudentProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-profile")
@RequiredArgsConstructor
public class StudentProfileController {
    private final StudentProfileService studentProfileService;

    @GetMapping("/me")
    public StudentProfileResponse getCurrentProfile() {
        return studentProfileService.getCurrentProfile();
    }

    @PutMapping("/me")
    public StudentProfileResponse updateCurrentProfile(
        @Valid @RequestBody UpdateStudentProfileRequest request
    ) {
        return studentProfileService.updateCurrentProfile(request);
    }
}
