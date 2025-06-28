package org.example.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Salle {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private int capacite;

    @OneToMany(mappedBy = "salle")
    private List<Seance> seances;


    public Salle(Long id, String nom, int capacite, List<Seance> seances) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.seances = seances;
    }

    public Salle() {
        super();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
