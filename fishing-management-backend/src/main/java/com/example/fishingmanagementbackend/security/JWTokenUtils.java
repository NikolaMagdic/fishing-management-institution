package com.example.fishingmanagementbackend.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.fishingmanagementbackend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTokenUtils {

    @Value("fishing-management-application")
    private String APP_NAME;
    
    @Value("DprLrW2OyqiAFiuWs14WO2TWp2EHtaX7a63dqrklkxrjaZMrcPhpX4hkZw803SQx5HpGc7VYBX8l82XlMZgggasf32g")
    public String SECRET;
    
    @Value("1200000")
    private int EXPIRES_IN;
    
    @Value("Authorization")
    private String AUTH_HEADER;
    
    // U slucaju da aplikacija moze da bude i za mobilne dodati ovde konstante
    private static final String AUDIENCE_WEB = "web";
    
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(String username) {
        return Jwts.builder().setIssuer(APP_NAME)
                             .setSubject(username)
                             .setAudience(AUDIENCE_WEB)
                             .setIssuedAt(new Date())
                             .setExpiration(generateExpirationDate())
                             .signWith(getSigningKey(), SIGNATURE_ALGORITHM).compact();
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN); 
    }
    
    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        
        return null;
    }
    
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
            e.printStackTrace();
        }
        return claims;
    }
    
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
            e.printStackTrace();
        }
        return username;
    }
    
    public Date getIssuedDateFromToken(String token) {
        Date issuedAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issuedAt = claims.getIssuedAt();
        } catch (Exception e) {
            issuedAt = null;
        }
        return issuedAt;
    }
    
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
    
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedDateFromToken(token);
        
        return (username != null 
                && username.equals(userDetails.getUsername())
                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
        
    }
    
    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }
    
    public int getExpiredIn() {
        return EXPIRES_IN;
    }
    
    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
    
}
