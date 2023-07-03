package com.example.fishingmanagementbackend.dto;

public class PasswordChangeRequestDTO {

    private String oldPassword;
    
    private String newPassword;
    
    public PasswordChangeRequestDTO() {}

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    
    
}
