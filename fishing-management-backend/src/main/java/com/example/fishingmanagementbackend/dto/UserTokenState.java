package com.example.fishingmanagementbackend.dto;

public class UserTokenState {

    private String jwtToken;
    
    private Long expiresIn;
    
    public UserTokenState() {
        this.jwtToken = null;
        this.expiresIn = null;
    }
    
    public UserTokenState(String jwtToken, long expiresIn) {
        this.jwtToken = jwtToken;
        this.expiresIn = expiresIn;
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

    @Override
    public String toString() {
        return "UserTokenState [jwtToken=" + jwtToken + ", expiresIn=" + expiresIn + "]";
    }
    
    
}
