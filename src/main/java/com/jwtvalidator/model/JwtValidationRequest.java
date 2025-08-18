package com.jwtvalidator.model;

public class JwtValidationRequest {
    
    private String jwt;

    public JwtValidationRequest() {}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
