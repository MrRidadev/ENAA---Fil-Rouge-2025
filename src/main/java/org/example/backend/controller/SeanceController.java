package org.example.backend.controller;

import org.example.backend.dto.SeanceRequest;
import org.example.backend.entity.Seance;
import org.example.backend.repository.SeanceRepository;
import org.example.backend.service.SeanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seance")
public class SeanceController {
    private SeanceRepository seanceRepository;

    private SeanceService seanceService;

    public SeanceController(SeanceRepository seanceRepository, SeanceService seanceService) {
        this.seanceRepository = seanceRepository;
        this.seanceService = seanceService;
    }


    @PostMapping("/addSeance")
    public Seance addSeance(@RequestBody SeanceRequest request) {
        return seanceService.addSeance(request.getDateHeure(), request.getFilmId(), request.getSalleId(),request.getNom_seance());
    }
    @GetMapping("/getAllSeance")
    public List<Seance> getAllSSeance() {
        return seanceService.getAllSeances();
    }


    // Delete
    @DeleteMapping("/deleteSeance/{id}")
    public String deleteSeance(@PathVariable Long id) {
       seanceService.deleteSeance(id);
       return "Seance deleted";
    }


    // Update
    @PutMapping("/updateSeance/{id}")
    public Seance updateSeance(@PathVariable Long id, @RequestBody SeanceRequest request) {
        return seanceService.updateSeance(
                id,
                request.getDateHeure(),
                request.getFilmId(),
                request.getSalleId(),
                request.getNom_seance()
        );
    }

    // count seance
    @GetMapping("/count/{titre}")
    public int countSeance(@PathVariable String titre) {
        return seanceService.countSeances(titre);
    }

}
