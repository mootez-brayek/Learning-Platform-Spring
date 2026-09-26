package com.education.learningplatform.User.service;

import com.education.learningplatform.User.dto.UserResponse;

public interface UserService {
    UserResponse getCurrentUser(String email);
}
