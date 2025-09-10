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


    // UPDATE - Mettre à jour un film avec image
    @PutMapping("/updateWithImage/{id}")
    public ResponseEntity<?> updateFilmWithImage(@PathVariable Long id,
                                                 @RequestPart String titre,
                                                 @RequestPart String genre,
                                                 @RequestPart String description,
                                                 @RequestPart String langue,
                                                 @RequestPart(value = "image_url", required = false) MultipartFile image) {
        try {
            Film updatedFilm = filmService.updateFilmWithImage(id, titre, genre, description, langue, image);
            return ResponseEntity.ok(updatedFilm);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la sauvegarde de l'image : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour du film : " + e.getMessage());
        }
    }

    // DELETE - Supprimer un film
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        try {
            filmService.deleteFilm(id);
            return ResponseEntity.ok("Film supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du film : " + e.getMessage());
        }
    }

    // get count film
    @GetMapping("/countFilm")
    public int countFilm(){
        return filmService.countFilm();
    }

}