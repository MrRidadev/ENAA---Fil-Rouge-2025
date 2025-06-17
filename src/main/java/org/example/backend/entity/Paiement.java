package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class Paiement {
    @Id
    @GeneratedValue
    private Long id;
    private float montant;
    private String date;
    private String methode;

    @OneToOne
    private Reservation reservation;
}
