package com.education.learningplatform.User.service;

import com.education.learningplatform.User.dto.StudentProfileResponse;
import com.education.learningplatform.User.dto.UpdateStudentProfileRequest;

public interface StudentProfileService {

    StudentProfileResponse getCurrentProfile();

    StudentProfileResponse updateCurrentProfile(
        UpdateStudentProfileRequest request
    );
}
