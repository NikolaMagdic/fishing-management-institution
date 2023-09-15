package com.example.fishingmanagementbackend.dto;

public class UserTokenState {

    private String jwtToken;
    
    private Long expiresIn;
    
    private String role;
    
    private Long correspondingTableId;
    
    public UserTokenState() {
        this.jwtToken = null;
        this.expiresIn = null;
        this.role = null;
        this.correspondingTableId = null;
    }
    
    public UserTokenState(String jwtToken, long expiresIn, String role, Long correspondingTableId) {
        this.jwtToken = jwtToken;
        this.expiresIn = expiresIn;
        this.role = role;
        this.correspondingTableId = correspondingTableId;
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

    public Long getCorrespondingTableId() {
        return correspondingTableId;
    }

    public void setCorrespondingTableId(Long correspondingTableId) {
        this.correspondingTableId = correspondingTableId;
    }

    @Override
    public String toString() {
        return "UserTokenState [jwtToken=" + jwtToken + ", expiresIn=" + expiresIn + ", role=" + role + ", correspondingTableId="
                + correspondingTableId + "]";
    }
    
}
