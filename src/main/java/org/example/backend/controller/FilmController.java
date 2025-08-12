package org.example.backend.controller;

import org.example.backend.entity.Film;
import org.example.backend.service.FilmService;
import org.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private ImageService imageService;

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

    // Mettre à jour un film
    @PutMapping("/putFilm/{id}")
    public ResponseEntity<?> putFilm(
            @PathVariable Long id,
            @RequestParam("titre") String titre,
            @RequestParam("genre") String genre,
            @RequestParam("description") String description,
            @RequestParam("langue") String langue,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            Film existingFilm = filmService.getFilmById(id);
            if (existingFilm == null) {
                return ResponseEntity.notFound().build();
            }

            existingFilm.setTitre(titre);
            existingFilm.setGenre(genre);
            existingFilm.setDescription(description);
            existingFilm.setLangue(langue);

            // Upload nouvelle image si présente
            if (image != null && !image.isEmpty()) {
                // Supprimer l'ancienne image
                if (existingFilm.getImageUrl() != null) {
                    imageService.deleteImage(existingFilm.getImageUrl());
                }
                // Upload la nouvelle
                String imageUrl = imageService.uploadImage(image);
                existingFilm.setImageUrl(imageUrl);
            }

            Film updatedFilm = filmService.updateFilm(existingFilm);
            return ResponseEntity.ok(updatedFilm);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'upload de l'image : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour du film : " + e.getMessage());
        }
    }

    // Mettre à jour un film (JSON)
    @PutMapping("/putFilmJson")
    public Film putFilmJson(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    // Supprimer un film par ID
    @DeleteMapping("/deleteFilm/{idFilm}")
    public ResponseEntity<String> deleteFilm(@PathVariable long idFilm) {
        try {
            Film film = filmService.getFilmById(idFilm);
            if (film != null && film.getImageUrl() != null) {
                // Supprimer l'image de Cloudinary
                imageService.deleteImage(film.getImageUrl());
            }
            filmService.deleteFilm(idFilm);
            return ResponseEntity.ok("Film supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression : " + e.getMessage());
        }
    }


}