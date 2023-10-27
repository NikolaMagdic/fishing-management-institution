package com.example.fishingmanagementbackend.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = -8414362650183876520L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String username;
    
    @Column
    private String password;

    @Column
    private boolean enabled;
    
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    
    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Column
    private LocalDate dateOfBirth;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", 
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;
    
    public User() {}
    
    public User(String username, String password, boolean enabled, String firstName, String lastName, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    @Override
    public Collection<Authority> getAuthorities() {
        return this.authorities;
    }
    
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
                + ", lastPasswordResetDate=" + lastPasswordResetDate + ", firstName=" + firstName + ", lastName="
                + lastName + ", dateOfBirth=" + dateOfBirth + ", authorities=" + authorities + "]";
    }
    
    
}
