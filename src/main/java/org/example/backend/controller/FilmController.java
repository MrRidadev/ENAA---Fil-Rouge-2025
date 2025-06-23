package org.example.backend.controller;

import org.example.backend.entity.Film;
import org.example.backend.service.FilmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/addFilm")
    public Film addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }
}
