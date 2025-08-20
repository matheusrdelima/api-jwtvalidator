package com.jwtvalidator.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JwtValidationRequest {

    @NotNull(message = "JWT token não pode ser nulo")
    @NotBlank(message = "JWT token não pode estar vazio")
    private String jwt;

    public JwtValidationRequest() {}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
