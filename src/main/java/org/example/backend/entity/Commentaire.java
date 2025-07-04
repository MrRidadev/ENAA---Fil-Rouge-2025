package org.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue
    private Long id;
    private String contenu;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Client client;
}
