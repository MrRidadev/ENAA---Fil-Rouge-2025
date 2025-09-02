package org.example.backend.repository;

import org.example.backend.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query(value = "select count(id) from seance s\n" +
            "inner join film f  \n" +
            "on f.id_film = s.film_id_film\n" +
            "where f.titre = :film ",nativeQuery = true)
    int countSeance(@Param("film") String film);
}
