package org.example.backend.controller;

import org.example.backend.dto.SeanceRequest;
import org.example.backend.entity.Seance;
import org.example.backend.repository.SeanceRepository;
import org.example.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seance")
@CrossOrigin(origins = "http://localhost:4200")
public class SeanceController {
    private SeanceRepository seanceRepository;
    @Autowired
    private SeanceService seanceService;
    public SeanceController(SeanceService seanceService) {
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
    public void deleteSeance(@PathVariable Long id) {
       seanceService.deleteSeance(id);
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

}
