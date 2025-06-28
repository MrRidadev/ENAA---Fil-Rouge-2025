package org.example.backend.controller;


import org.example.backend.entity.Salle;
import org.example.backend.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salle")
public class SalleController {


    @Autowired
    private SalleService salleService;



    @PostMapping("/addSalle")
    public Salle addSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);
    }
}
