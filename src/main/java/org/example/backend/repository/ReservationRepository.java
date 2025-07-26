package org.example.backend.repository;

import org.example.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Trouver les réservations par client
    List<Reservation> findByClientId(Long clientId);

    // Trouver les réservations par séance
    List<Reservation> findBySeanceId(Long seanceId);

    // Trouver les réservations par date
    List<Reservation> findByDate(String date);

    // Compter le nombre de places réservées pour une séance
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.seance.id = :seanceId")
    int countReservationsBySeanceId(@Param("seanceId") Long seanceId);

    // Vérifier si une place est déjà réservée pour une séance
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.seance.id = :seanceId AND r.numPlace = :numPlace")
    int countBySeanceIdAndNumPlace(@Param("seanceId") Long seanceId, @Param("numPlace") int numPlace);
}
