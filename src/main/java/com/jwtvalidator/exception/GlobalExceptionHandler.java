package com.jwtvalidator.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jwtvalidator.core.metrics.JwtMetrics;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final JwtMetrics jwtMetrics;

    @Autowired
    public GlobalExceptionHandler(JwtMetrics jwtMetrics) {
        this.jwtMetrics = jwtMetrics;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleJwtValidationException(ValidationException ex) {
        jwtMetrics.incrementValidationFailureCount();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("falso");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("falso");
    }
}
