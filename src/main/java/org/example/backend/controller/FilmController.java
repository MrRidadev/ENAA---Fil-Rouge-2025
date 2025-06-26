package org.example.backend.controller;


import org.example.backend.entity.Film;
import org.example.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/addFilm")
    public Film addFilm (@RequestBody Film film){
         return filmService.addFilm(film);
    }

    // afficher list film
    @GetMapping("/getAllFilm")
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }
}
