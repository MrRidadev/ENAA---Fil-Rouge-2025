package org.example.backend.Services;

import org.example.backend.entity.Salle;
import org.example.backend.repository.SalleRepository;
import org.example.backend.service.SalleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
