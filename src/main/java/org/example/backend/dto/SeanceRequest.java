package org.example.backend.dto;

public class SeanceRequest {
    private String nom_seance;
    private String dateHeure;
    private Long filmId;
    private Long salleId;

    public String getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getSalleId() {
        return salleId;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public String getNom_seance() {
        return nom_seance;
    }
    public void setNom_seance(String nom_seance) {
        this.nom_seance = nom_seance;
    }
}
