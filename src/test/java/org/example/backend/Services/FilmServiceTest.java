package org.example.backend.Services;

import org.example.backend.entity.Film;
import org.example.backend.repository.FilmRepository;
import org.example.backend.service.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"upload.directory=C:/temp/uploads"})@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void testAddAndGetFilm() throws Exception {
        Film film = new Film();
        film.setTitre("Inception");
        film.setGenre("Sci-fi");
        film.setDescription("A mind-bending thriller");
        film.setLangue("English");

        Film saved = filmService.saveFilmWithImage(
                film.getTitre(),
                film.getGenre(),
                film.getDescription(),
                film.getLangue(),
                null
        );

        Assertions.assertNotNull(saved.getIdFilm());
        Assertions.assertEquals("Inception", saved.getTitre());
    }


    @Test
    public void testUpdateFilm() throws Exception {
        // Sauvegarde initiale
        Film saved = filmService.saveFilmWithImage(
                "Matrix",
                "Action",
                "Neo discovers reality",
                "English",
                null
        );

        // Mise à jour
        Film updated = filmService.updateFilmWithImage(
                saved.getIdFilm(),
                "The Matrix Reloaded",
                saved.getGenre(),
                saved.getDescription(),
                saved.getLangue(),
                null
        );

        Assertions.assertEquals("The Matrix Reloaded", updated.getTitre());
    }


    @Test
    public void testDeleteFilm() throws Exception {
        Film saved = filmService.saveFilmWithImage(
                "Avatar",
                "Fantasy",
                "Pandora's story",
                "English",
                null
        );

        Long id = saved.getIdFilm();
        filmService.deleteFilm(id);

        Assertions.assertFalse(filmRepository.findById(id).isPresent());
    }


}
