package com.example.fishingmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.AuthenticationRequestDTO;
import com.example.fishingmanagementbackend.dto.PasswordChangeRequestDTO;
import com.example.fishingmanagementbackend.dto.UserTokenState;
import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.security.JWTokenUtils;
import com.example.fishingmanagementbackend.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    
    @Autowired 
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JWTokenUtils tokenUtils;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> login(@RequestBody AuthenticationRequestDTO authRequest) {
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        User user = (User)authentication.getPrincipal();
        
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        String role = user.getAuthorities().iterator().next().getAuthority();
        
        Long userId = user.getId();
        
        UserTokenState userTokenState = new UserTokenState(jwt, expiresIn, role, userId);
        
        return ResponseEntity.ok(userTokenState);
    }
    
    @GetMapping(value = "/confirm-registration", produces = "text/html; charset=utf-8")
    public ResponseEntity<String> confirmRegistration(@RequestParam String token) {
        String responseHtmlPage = userService.confirmRegistration(token);
        return ResponseEntity.ok(responseHtmlPage);
    }
    
    @PutMapping
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDTO passwordChangeRequest) {
        
        try {
            userService.changePassword(passwordChangeRequest.getOldPassword(), passwordChangeRequest.getNewPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    
    }
    
    
    
}
