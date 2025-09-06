package org.example.backend.controller;


import org.example.backend.entity.Salle;
import org.example.backend.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salle")
public class SalleController {


    @Autowired
    private SalleService salleService;



    @PostMapping("/addSalle")
    public Salle addSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);
    }

    @GetMapping("/getAll")
    public List<Salle> getAll() {
        return salleService.getAllSalles(); 
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
    }
}
