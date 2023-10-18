package com.asset.dto;

public class UserLoginResponse {
    private String token;
    private String username;
    private String role;
    private String status;

    // Getter, Setter, and Constructors


    public UserLoginResponse(String token, String username, String role, String status) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
