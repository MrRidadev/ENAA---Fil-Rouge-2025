package org.example.backend.Services;

import org.example.backend.entity.Salle;
import org.example.backend.repository.SalleRepository;
import org.example.backend.service.SalleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SalleServiceTest {

    @Autowired
    private SalleService salleService;

    @Autowired
    private SalleRepository salleRepository;

    @Test
    public void testAddSalle() {
        Salle salle = new Salle();
        salle.setNom("Salle 1");
        salle.setCapacite(100);

        Salle saved = salleService.addSalle(salle);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Salle 1", saved.getNom());
        Assertions.assertEquals(100, saved.getCapacite());
    }

    @Test
    public void testGetAllSalles() {
        Salle salle1 = new Salle();
        salle1.setNom("Salle A");
        salle1.setCapacite(50);
        salleService.addSalle(salle1);

        Salle salle2 = new Salle();
        salle2.setNom("Salle B");
        salle2.setCapacite(80);
        salleService.addSalle(salle2);

        List<Salle> salles = salleService.getAllSalles();

        Assertions.assertEquals(3, salles.size());
    }

}
