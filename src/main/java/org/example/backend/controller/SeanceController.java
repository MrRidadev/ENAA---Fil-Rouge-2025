package org.example.backend.controller;

import org.example.backend.dto.SeanceRequest;
import org.example.backend.entity.Seance;
import org.example.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seance")
public class SeanceController {

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
}
