package com.education.learningplatform.auth.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException {
    private final String messageKey;

    public InvalidCredentialsException(String messageKey) {
        this.messageKey = messageKey;
    }
}
