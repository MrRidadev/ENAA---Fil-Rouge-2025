package org.example.backend.controller;

import org.example.backend.dto.ReservationDTO;
import org.example.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Créer une nouvelle réservation
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de la réservation : " + e.getMessage());
        }
    }

    // Obtenir toutes les réservations
    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    // Obtenir une réservation par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        try {
            ReservationDTO reservation = reservationService.getReservationById(id);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtenir les réservations par client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByClient(@PathVariable Long clientId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByClient(clientId);
        return ResponseEntity.ok(reservations);
    }

    // Obtenir les réservations par séance
    @GetMapping("/seance/{seanceId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsBySeance(@PathVariable Long seanceId) {
        List<ReservationDTO> reservations = reservationService.getReservationsBySeance(seanceId);
        return ResponseEntity.ok(reservations);
    }

    // Mettre à jour une réservation
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        try {
            ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationDTO);
            return ResponseEntity.ok(updatedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    // Supprimer une réservation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Réservation supprimée avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    // Endpoint pour vérifier la disponibilité d'une place
    @GetMapping("/check-availability/{seanceId}/{numPlace}")
    public ResponseEntity<Boolean> checkPlaceAvailability(@PathVariable Long seanceId, @PathVariable int numPlace) {
        try {
            List<ReservationDTO> reservations = reservationService.getReservationsBySeance(seanceId);
            boolean isAvailable = reservations.stream()
                    .noneMatch(r -> r.getNumPlace() == numPlace);
            return ResponseEntity.ok(isAvailable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}