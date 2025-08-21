package com.jwtvalidator.model;

import java.util.Map;

public class Claims {

    private String name;
    private String role;
    private String seed;
    private Map<String, String> allClaims;

    public Claims() {}

    public Claims(String name, String role, String seed, Map<String, String> allClaims) {
        this.name = name;
        this.role = role;
        this.seed = seed;
        this.allClaims = allClaims;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Map<String, String> getAllClaims() {
        return allClaims;
    }

    public void setAllClaims(Map<String, String> allClaims) {
        this.allClaims = allClaims;
    }
}
