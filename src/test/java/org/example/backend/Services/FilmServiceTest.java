package org.example.backend.Services;

import org.example.backend.entity.Film;
import org.example.backend.repository.FilmRepository;
import org.example.backend.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmRepository.deleteAll(); // Nettoyage de la BDD entre les tests
    }

    @Test
    void testAddFilm() {
        // Arrange
        Film film = new Film();
        film.setTitre("Inception");
        film.setGenre("Science Fiction");
        film.setDescription("Un thriller psychologique.");
        film.setLangue("Anglais");

        // Act
        Film savedFilm = filmService.addFilm(film);

        // Assert
        assertNotNull(savedFilm.getIdFilm());
        assertEquals("Inception", savedFilm.getTitre());
    }

}
