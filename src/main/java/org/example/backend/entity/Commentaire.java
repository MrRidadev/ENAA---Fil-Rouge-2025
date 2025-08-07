package org.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue
    private Long id;
    private String contenu;

    @ManyToOne
    @JsonBackReference("film-commentaires")
    private Film film;

    @ManyToOne
    private Client client;
}
