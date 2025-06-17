package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private int numPlace;

    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Paiement paiement;
}
