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
    
    public String confirmRegistration(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        
        user.setEnabled(true);
        userRepository.save(user);
        
        return "<html><head><meta charset=\"UTF-8\"><title>Registracija uspešna</title></head><body><h1>"
                + "Uspešno ste se registrovali na aplikaciju sistema za upravljanje ribolovnim vodama.</h1>"
                + " <p>Možete zatvoriti ovaj prozor i prijaviti se u aplikaciju sa vašim korisničkim imenom i lozinkom.</p><body></html>";
    }

    public Long getIdOfUserInCorrespondingRoleTable(String username, String role) {
        Long id;
        
        switch (role) {
        case "ROLE_ADMIN": 
            // TODO: Implementirati Admin Repository
            id = 1L;
            break;   
        case "ROLE_FISHERMAN": 
            id = this.userRepository.findByUsername(username).getFisherman().getId();
            break;
        case "ROLE_KEEPER":
            id = this.userRepository.findByUsername(username).getKeeper().getId();
            break;
        default:
            id = 0L;
            break;
        }
        
        return id;
    }
    
}
