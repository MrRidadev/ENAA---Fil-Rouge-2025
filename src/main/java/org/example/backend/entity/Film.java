package org.example.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Film {

    @Id
    @GeneratedValue
    private Long idFilm;
    private String titre;
    private String genre;
    private String description;
    private String langue;

    @OneToMany(mappedBy = "film")
    private List<Seance> seances;

    @OneToMany(mappedBy = "film")
    private List<Commentaire> commentaires;
}
