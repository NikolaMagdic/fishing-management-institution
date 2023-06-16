package com.example.fishingmanagementbackend.security.auth;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fishingmanagementbackend.security.JWTokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenAuthenticationFilter extends OncePerRequestFilter{

    private JWTokenUtils tokenUtils;
    
    private UserDetailsService userDetailsService;
    
    public TokenAuthenticationFilter(JWTokenUtils token, UserDetailsService userDetailsService) {
        this.tokenUtils = token;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String username;
        String authToken = tokenUtils.getToken(request);
        
        if(authToken != null) {
            username = tokenUtils.getUsernameFromToken(authToken);
            
            if(username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                if(tokenUtils.validateToken(authToken, userDetails)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
