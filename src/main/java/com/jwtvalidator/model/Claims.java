package com.jwtvalidator.model;

public class Claims {
    
    private String name;
    private String role;
    private String seed;

    public Claims() {}

    public Claims(String name, String role, String seed) {
        this.name = name;
        this.role = role;
        this.seed = seed;
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
}
