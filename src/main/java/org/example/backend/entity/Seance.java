package org.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Seance {

    @Id
    @GeneratedValue
    private Long id;
    private String dateHeure;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Salle salle;
}
