package org.example.backend.controller;

import org.example.backend.entity.Film;
import org.example.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/film")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    @Autowired
    private FilmService filmService;


    // Ajouter un film avec image
    @PostMapping("/addFilm")
    public Film addFilm(@RequestPart String titre,
                        @RequestPart String genre,
                        @RequestPart String description,
                        @RequestPart String langue,
                        @RequestPart("image_url") MultipartFile image) throws IOException {
        return filmService.saveFilmWithImage(titre, genre, description, langue, image);
    }



    // Afficher la liste des films
    @GetMapping("/getAllFilm")
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }



    // Mettre à jour un film (JSON)
    @PutMapping("/putFilmJson")
    public Film putFilmJson(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }




}