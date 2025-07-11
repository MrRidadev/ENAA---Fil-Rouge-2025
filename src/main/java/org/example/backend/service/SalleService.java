package org.example.backend.service;


import org.example.backend.entity.Salle;
import org.example.backend.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalleService {


    @Autowired
    private SalleRepository salleRepository;



    public Salle addSalle(Salle salle) {
        return salleRepository.save(salle);
    }
}
