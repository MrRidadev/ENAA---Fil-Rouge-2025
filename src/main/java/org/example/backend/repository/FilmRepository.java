package org.example.backend.repository;

import org.example.backend.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmRepository extends JpaRepository<Film, Long> {

    //count client
    @Query(value = "select count(*) from film",nativeQuery = true)
    int countFilms();
}
