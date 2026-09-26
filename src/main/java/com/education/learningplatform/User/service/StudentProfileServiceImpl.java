package com.education.learningplatform.User.service;

import com.education.learningplatform.Security.JwtUtil;
import com.education.learningplatform.User.dto.StudentProfileResponse;
import com.education.learningplatform.User.dto.UpdateStudentProfileRequest;
import com.education.learningplatform.User.model.StudentProfile;
import com.education.learningplatform.User.model.User;
import com.education.learningplatform.User.repository.StudentProfileRepository;
import com.education.learningplatform.User.repository.UserRepository;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService{

    private final StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfileResponse getCurrentProfile() {

        User user = JwtUtil.getCurrentUser();

        StudentProfile profile = studentProfileRepository
            .findByUserId(user.getId())
            .orElseThrow(() ->
                new ResourceNotFoundException("studentProfile.notFound")
            );

        return new StudentProfileResponse(
            profile.getId(),
            profile.getDateOfBirth(),
            profile.getEducationLevel(),
            profile.getAvatar()
        );
    }

    @Override
    public StudentProfileResponse updateCurrentProfile(UpdateStudentProfileRequest request) {
        User user = JwtUtil.getCurrentUser();

        StudentProfile profile = studentProfileRepository
            .findByUserId(user.getId())
            .orElseGet(() ->
                StudentProfile.builder()
                    .user(user)
                    .build()
            );

        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setEducationLevel(request.getEducationLevel());
        profile.setAvatar(request.getAvatar());

        StudentProfile savedProfile =
            studentProfileRepository.save(profile);

        return new StudentProfileResponse(
            savedProfile.getId(),
            savedProfile.getDateOfBirth(),
            savedProfile.getEducationLevel(),
            savedProfile.getAvatar()
        );
    }
}
