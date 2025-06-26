package org.example.backend.dto;

import org.example.backend.entity.Role;

public class RegisterRequest {

    private String nomComplet;
    private String email;
    private String modPass;
    private Role role;

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

    public String getModPass() {
        return modPass;
    }

    public void setModPass(String modPass) {
        this.modPass = modPass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
