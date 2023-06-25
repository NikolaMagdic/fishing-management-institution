package com.example.fishingmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.AuthenticationRequestDTO;
import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.security.JWTokenUtils;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired 
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JWTokenUtils tokenUtils;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestDTO authRequest) {
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        User user = (User)authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        // razmotriti da li je potrebno ovde da vracam DTO koji ukljucuje i expiresIn atribut
        return ResponseEntity.ok(jwt);
    }
}
