package com.AuthService.Dto;

public class RegisterDto {
    private String email;
    private String password;
    private String username;

    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
