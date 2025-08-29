package com.jwtvalidator.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.presentation.dto.JwtValidationRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class JwtValidatorController {

    private final JwtValidatorService jwtValidatorService;

    public JwtValidatorController(JwtValidatorService jwtValidatorService) {
        this.jwtValidatorService = jwtValidatorService;
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@Valid @RequestBody JwtValidationRequest request) {
        jwtValidatorService.validate(request.getJwt());
        return ResponseEntity.ok("verdadeiro");
    }
}
