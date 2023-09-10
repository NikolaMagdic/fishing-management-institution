package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    
    VerificationToken findByUser(User user);
}
