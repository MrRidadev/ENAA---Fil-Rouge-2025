package org.example.backend.entity;


import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nomComplet;
    private String email;
    private String modPass;
    private Role role = Role.CLIENT;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
