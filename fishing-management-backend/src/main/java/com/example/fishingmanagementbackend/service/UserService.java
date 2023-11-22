package com.example.fishingmanagementbackend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.model.VerificationToken;
import com.example.fishingmanagementbackend.repository.UserRepository;
import com.example.fishingmanagementbackend.repository.VerificationTokenRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Lazy // zbog cirkularnih dependency-ja
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username);
        
        if (user == null) 
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

        return user;
    }
    
    public User findUserByUsername(String username) {
        
        User user = userRepository.findByUsername(username); 
           
        return user;
    }
    
    /**Izmena lozinke korisnika*/
    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        
        if(authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            return;
        }
        
        User user = (User) loadUserByUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    
    }
    
    public String confirmRegistration(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        
        user.setEnabled(true);
        userRepository.save(user);
        
        File registrationSuccessfullHTML = new File ("src/main/resources/templates/RegistrationSuccessful.html");
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(registrationSuccessfullHTML));
            String htmlString;
            while ((htmlString = in.readLine()) != null) {
                builder.append(htmlString);
            }
            in.close();
        } catch (IOException e) {
        }
        
        return builder.toString();
    }

}
