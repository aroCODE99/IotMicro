package com.AuthService.Entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
               name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRoles;
    
    public UUID getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    
    }
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }

    public Set<Role> getUserRoles() {
        return this.userRoles;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
