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

        Film saved = filmService.saveFilmWithImage(film);

        Assertions.assertNotNull(saved.getIdFilm());
        Assertions.assertEquals("Inception", saved.getTitre());
    }

    @Test
    public void testUpdateFilm() {
        Film film = new Film();
        film.setTitre("Matrix");
        film.setGenre("Action");
        film.setDescription("Neo discovers reality");
        film.setLangue("English");

        Film saved = filmService.saveFilmWithImage(film);
        saved.setTitre("The Matrix Reloaded");

        Film updated = filmService.updateFilm(saved);

        Assertions.assertEquals("The Matrix Reloaded", updated.getTitre());
    }


    @Test
    public void testDeleteFilm() {
        Film film = new Film();
        film.setTitre("Avatar");
        film.setGenre("Fantasy");
        film.setDescription("Pandora's story");
        film.setLangue("English");

        Film saved = filmService.addFilm(film);
        Long id = saved.getIdFilm();

        filmService.deleteFilm(id);

        Assertions.assertFalse(filmRepository.findById(id).isPresent());
    }

}
