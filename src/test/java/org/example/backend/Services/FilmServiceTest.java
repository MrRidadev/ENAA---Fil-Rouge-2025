package org.example.backend.Services;

import org.example.backend.entity.Film;
import org.example.backend.repository.FilmRepository;
import org.example.backend.service.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void testAddAndGetFilm() {
        Film film = new Film();
        film.setTitre("Inception");
        film.setGenre("Sci-fi");
        film.setDescription("A mind-bending thriller");
        film.setLangue("English");

        Film saved = filmService.addFilm(film);

        Assertions.assertNotNull(saved.getIdFilm());
        Assertions.assertEquals("Inception", saved.getTitre());
    }



}
