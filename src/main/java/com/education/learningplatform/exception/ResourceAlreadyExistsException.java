package com.education.learningplatform.exception;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException{

    private final String messageKey;

    public ResourceAlreadyExistsException(String messageKey) {
        this.messageKey = messageKey;
    }
}
