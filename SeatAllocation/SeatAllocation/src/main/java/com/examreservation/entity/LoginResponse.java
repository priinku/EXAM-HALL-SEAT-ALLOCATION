package com.examreservation.entity;

public class LoginResponse {
    private String username;
    private String role;

    // Constructor, Getters and Setters
    // ...

    public LoginResponse(String username) {
        this.username = username;
    }

    public LoginResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }
}