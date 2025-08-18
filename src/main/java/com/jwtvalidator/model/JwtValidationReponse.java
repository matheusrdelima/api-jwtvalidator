package com.jwtvalidator.model;

public class JwtValidationReponse {
    
    private Boolean valid;

    public JwtValidationReponse() {}

    public JwtValidationReponse(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
