package com.example.fishingmanagementbackend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.model.VerificationToken;
import com.example.fishingmanagementbackend.repository.VerificationTokenRepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    
    @Async
    public void sendMailAsync(User user, String fishermanName) throws MailException, InterruptedException {
        String token = UUID.randomUUID().toString();
        createVerificationToken(token, user);
        String confirmUrl = "http://localhost:8080/api/auth/confirm-registration?token=";
        
        System.out.println("Sending mail...");
        
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Potvrda registracije");
        mail.setText("Pozdrav " + fishermanName +  ". " + "Potvrdite registraciju na stranicu ribolovnog udruženja klikom na link.\n" + confirmUrl + token);
        javaMailSender.send(mail);
        
        System.out.println("Email poslat!");
    }
    
    public VerificationToken createVerificationToken(String token, User user) {
        VerificationToken newToken = new VerificationToken(token);
        newToken.setUser(user);
        verificationTokenRepository.save(newToken);
        return newToken;
    } 
}
