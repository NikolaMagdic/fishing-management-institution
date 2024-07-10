package com.example.fishingmanagementbackend.exceptions;

public class BadRequestException extends RuntimeException {
    
    private static final long serialVersionUID = 4095118751680855251L;

    public BadRequestException(String message) {
        super(message);
    }
}
