package com.education.learningplatform.User.service;

import com.education.learningplatform.User.dto.UserResponse;
import com.education.learningplatform.User.model.User;
import com.education.learningplatform.User.repository.UserRepository;
import com.education.learningplatform.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse getCurrentUser(String email) {
        User user = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new ResourceNotFoundException("user.notFound")
            );

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName()
        );
    }
}
