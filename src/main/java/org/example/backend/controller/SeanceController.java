package org.example.backend.controller;

import org.example.backend.dto.SeanceRequest;
import org.example.backend.entity.Seance;
import org.example.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return seanceService.addSeance(request.getDateHeure(), request.getFilmId(), request.getSalleId());
    }
}
