package com.education.learningplatform.auth.service;

import com.education.learningplatform.Security.JwtUtil;
import com.education.learningplatform.User.model.Role;
import com.education.learningplatform.User.model.User;
import com.education.learningplatform.User.repository.UserRepository;
import com.education.learningplatform.auth.dto.AuthResponse;
import com.education.learningplatform.auth.dto.LoginRequest;
import com.education.learningplatform.auth.dto.RegisterRequest;
import com.education.learningplatform.auth.exception.EmailAlreadyExistsException;
import com.education.learningplatform.auth.exception.InvalidCredentialsException;
import com.education.learningplatform.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("user.emailAlreadyExists");
        }

        if (request.getRole() == Role.ADMIN) {
            throw new InvalidCredentialsException("auth.adminRegistrationNotAllowed");
        }

        User user = User.builder()
            .email(request.getEmail())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .active(true)
            .role(request.getRole())
            .build();

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() ->
                new InvalidCredentialsException("auth.invalidCredentials")
            );

        if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPasswordHash()
        )) {
            throw new InvalidCredentialsException("auth.invalidCredentials");
        }

        if (!user.isActive()) {
            throw new RuntimeException("auth.accountInactive");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new AuthResponse(token);
    }
}
