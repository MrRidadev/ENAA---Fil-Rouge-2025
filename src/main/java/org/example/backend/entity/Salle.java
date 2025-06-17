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
}
