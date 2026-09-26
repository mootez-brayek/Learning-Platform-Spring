package com.education.learningplatform.auth.service;

import com.education.learningplatform.auth.dto.AuthResponse;
import com.education.learningplatform.auth.dto.LoginRequest;
import com.education.learningplatform.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
