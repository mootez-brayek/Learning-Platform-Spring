package com.education.learningplatform.exception;

import com.education.learningplatform.auth.exception.InvalidCredentialsException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
        ResourceNotFoundException exception
    ) {

        String message = messageSource.getMessage(
            exception.getMessageKey(),
            null,
            LocaleContextHolder.getLocale()
        );

        ErrorResponse response = new ErrorResponse(
            "RESOURCE_NOT_FOUND",
            message,
            null,
            LocalDateTime.now()
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(response);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(
        ResourceAlreadyExistsException exception
    ) {

        String message = messageSource.getMessage(
            exception.getMessageKey(),
            null,
            LocaleContextHolder.getLocale()
        );

        ErrorResponse response = new ErrorResponse(
            "RESOURCE_ALREADY_EXISTS",
            message,
            null,
            LocalDateTime.now()
        );

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
        InvalidCredentialsException exception
    ) {

        String message = messageSource.getMessage(
            exception.getMessageKey(),
            null,
            LocaleContextHolder.getLocale()
        );

        ErrorResponse response = new ErrorResponse(
            "UNAUTHORIZED",
            message,
            null,
            LocalDateTime.now()
        );

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException exception
    ) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                errors.put(
                    error.getField(),
                    error.getDefaultMessage()
                )
            );

        String message = messageSource.getMessage(
            "validation.failed",
            null,
            LocaleContextHolder.getLocale()
        );

        ErrorResponse response = new ErrorResponse(
            "VALIDATION_FAILED",
            message,
            errors,
            LocalDateTime.now()
        );
        System.out.println("ARABIC TEST: " + message);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
        Exception exception
    ) {

        String message = messageSource.getMessage(
            "server.internalError",
            null,
            LocaleContextHolder.getLocale()
        );

        ErrorResponse response = new ErrorResponse(
            "INTERNAL_SERVER_ERROR",
            message,
            null,
            LocalDateTime.now()
        );

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }
}
