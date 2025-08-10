package org.example.backend.service;

import org.example.backend.entity.Film;
import org.example.backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film saveFilmWithImage(String titre, String genre, String description, String langue, MultipartFile image) throws IOException {
        // Sauvegarde de l'image sur le disque
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.copy(image.getInputStream(), filePath);

        // Sauvegarde du film avec chemin d'image
        Film film = new Film();
        film.setTitre(titre);
        film.setGenre(genre);
        film.setDescription(description);
        film.setLangue(langue);
        film.setImageUrl(fileName);

        return filmRepository.save(film);
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film updateFilm(Film film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public Film getFilmById(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.orElse(null);
    }

    // Méthodes spécifiques pour la gestion des images
    public Film updateFilmImage(Long id, String imageUrl) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            film.setImageUrl(imageUrl);
            return filmRepository.save(film);
        }
        return null;
    }

    public List<Film> findFilmsByGenre(String genre) {
        // Vous pouvez ajouter cette méthode dans le repository si nécessaire
        return filmRepository.findAll().stream()
                .filter(film -> film.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Film> searchFilms(String keyword) {
        // Recherche par titre ou genre
        return filmRepository.findAll().stream()
                .filter(film ->
                        film.getTitre().toLowerCase().contains(keyword.toLowerCase()) ||
                                film.getGenre().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
    }
}