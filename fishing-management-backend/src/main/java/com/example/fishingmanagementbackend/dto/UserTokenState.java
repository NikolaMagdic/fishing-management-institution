package com.example.fishingmanagementbackend.dto;

public class UserTokenState {

    private String jwtToken;
    
    private Long expiresIn;
    
    private String role;
    
    public UserTokenState() {
        this.jwtToken = null;
        this.expiresIn = null;
        this.role = null;
    }
    
    public UserTokenState(String jwtToken, long expiresIn, String role) {
        this.jwtToken = jwtToken;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserTokenState [jwtToken=" + jwtToken + ", expiresIn=" + expiresIn + ", role=" + role + "]";
    }
    
    
}
