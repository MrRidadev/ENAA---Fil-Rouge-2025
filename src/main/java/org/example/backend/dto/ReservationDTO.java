package org.example.backend.dto;

public class ReservationDTO {
    private Long id;
    private String date;
    private int numPlace;
    private Long clientId;
    private Long seanceId;
    private String clientNom;
    private String filmTitre;
    private String salleNom;
    private String seanceDateHeure;

    public ReservationDTO() {}

    public ReservationDTO(Long id, String date, int numPlace, Long clientId, Long seanceId, String clientNom, String filmTitre, String salleNom, String seanceDateHeure) {
        this.id = id;
        this.date = date;
        this.numPlace = numPlace;
        this.clientId = clientId;
        this.seanceId = seanceId;
        this.clientNom = clientNom;
        this.filmTitre = filmTitre;
        this.salleNom = salleNom;
        this.seanceDateHeure = seanceDateHeure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumPlace() {
        return numPlace;
    }

    public void setNumPlace(int numPlace) {
        this.numPlace = numPlace;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Long seanceId) {
        this.seanceId = seanceId;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getFilmTitre() {
        return filmTitre;
    }

    public void setFilmTitre(String filmTitre) {
        this.filmTitre = filmTitre;
    }

    public String getSalleNom() {
        return salleNom;
    }

    public void setSalleNom(String salleNom) {
        this.salleNom = salleNom;
    }

    public String getSeanceDateHeure() {
        return seanceDateHeure;
    }

    public void setSeanceDateHeure(String seanceDateHeure) {
        this.seanceDateHeure = seanceDateHeure;
    }
}
