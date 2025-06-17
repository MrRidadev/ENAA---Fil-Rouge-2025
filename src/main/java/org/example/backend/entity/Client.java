package org.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "client")
    private List<Commentaire> commentaires;
}
