package com.example.fishingmanagementbackend.exceptions;

public class ForbiddenException extends RuntimeException {
    
    private static final long serialVersionUID = 5348221252427264561L;

    public ForbiddenException(String message) {
        super(message);
    } 

}
