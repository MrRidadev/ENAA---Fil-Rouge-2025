package org.example.backend.service;


import org.example.backend.entity.Film;
import org.example.backend.entity.Salle;
import org.example.backend.entity.Seance;
import org.example.backend.repository.FilmRepository;
import org.example.backend.repository.SalleRepository;
import org.example.backend.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SeanceRepository seanceRepository;

    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public Seance addSeance(String dateHeure, Long filmId, Long salleId, String nomSeance) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film non trouvé : " + filmId));
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle non trouvée : " + salleId));

        Seance seance = new Seance();
        seance.setDateHeure(dateHeure);
        seance.setFilm(film);
        seance.setSalle(salle);

        return seanceRepository.save(seance);
    }
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }
}
