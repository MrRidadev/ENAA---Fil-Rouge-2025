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
import java.nio.file.StandardCopyOption;
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
        // Dossier où sauvegarder les images
        String uploadDirectory = "uploads";
        Path uploadPath = Paths.get(uploadDirectory);

        // Créer le dossier s'il n'existe pas
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Renommer le fichier pour éviter les conflits
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Sauvegarde de l'image sur le disque
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Sauvegarde du film avec chemin de l'image
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


}