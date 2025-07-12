package org.example.backend.dto;

import org.example.backend.entity.Role;

public class LoginResponse {

    private String message;
    private boolean success;
    private Long userId;
    private String nomComplet;
    private String email;
    private Role role;

    public LoginResponse() {}

    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public LoginResponse(String message, boolean success, Long userId, String nomComplet, String email, Role role) {
        this.message = message;
        this.success = success;
        this.userId = userId;
        this.nomComplet = nomComplet;
        this.email = email;
        this.role = role;
    }

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
