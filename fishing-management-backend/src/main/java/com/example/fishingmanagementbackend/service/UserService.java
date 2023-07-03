package com.example.fishingmanagementbackend.service;

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
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Lazy // zbog cirkularnih dependency-ja
    private AuthenticationManager authenticationManager;
    
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
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        
        if(authenticationManager != null) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
        
        User user = (User) loadUserByUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    
    }

}
