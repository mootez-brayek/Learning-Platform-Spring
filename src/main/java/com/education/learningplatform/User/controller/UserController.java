package com.education.learningplatform.User.controller;

import com.education.learningplatform.User.dto.UserResponse;
import com.education.learningplatform.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(
        Authentication authentication
    ) {
        return userService.getCurrentUser(
            authentication.getName()
        );
    }
}
