package org.example.backend.service;

import org.example.backend.dto.ReservationDTO;
import org.example.backend.entity.Client;
import org.example.backend.entity.Reservation;
import org.example.backend.entity.Seance;
import org.example.backend.repository.ClientRepository;
import org.example.backend.repository.ReservationRepository;
import org.example.backend.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    // Créer une réservation
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        // Vérifier si le client existe
        Client client = clientRepository.findById(reservationDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID : " + reservationDTO.getClientId()));

        // Vérifier si la séance existe
        Seance seance = seanceRepository.findById(reservationDTO.getSeanceId())
                .orElseThrow(() -> new RuntimeException("Séance non trouvée avec l'ID : " + reservationDTO.getSeanceId()));

        // Vérifier si la place est déjà réservée
        int existingReservations = reservationRepository.countBySeanceIdAndNumPlace(
                reservationDTO.getSeanceId(), reservationDTO.getNumPlace());
        if (existingReservations > 0) {
            throw new RuntimeException("La place " + reservationDTO.getNumPlace() + " est déjà réservée pour cette séance");
        }

        // Vérifier la capacité de la salle
        int totalReservations = reservationRepository.countReservationsBySeanceId(reservationDTO.getSeanceId());
        if (totalReservations >= seance.getSalle().getCapacite()) {
            throw new RuntimeException("La séance est complète");
        }

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setDate(reservationDTO.getDate() != null ? reservationDTO.getDate() :
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        reservation.setNumPlace(reservationDTO.getNumPlace());
        reservation.setClient(client);
        reservation.setSeance(seance);

        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    // Obtenir toutes les réservations
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtenir une réservation par ID
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));
        return convertToDTO(reservation);
    }

    // Obtenir les réservations par client
    public List<ReservationDTO> getReservationsByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtenir les réservations par séance
    public List<ReservationDTO> getReservationsBySeance(Long seanceId) {
        return reservationRepository.findBySeanceId(seanceId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Mettre à jour une réservation
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));

        // Vérifier si la nouvelle place est disponible (si elle a changé)
        if (reservationDTO.getNumPlace() != existingReservation.getNumPlace()) {
            int existingReservations = reservationRepository.countBySeanceIdAndNumPlace(
                    existingReservation.getSeance().getId(), reservationDTO.getNumPlace());
            if (existingReservations > 0) {
                throw new RuntimeException("La place " + reservationDTO.getNumPlace() + " est déjà réservée pour cette séance");
            }
        }

        // Mettre à jour les champs
        if (reservationDTO.getDate() != null) {
            existingReservation.setDate(reservationDTO.getDate());
        }
        existingReservation.setNumPlace(reservationDTO.getNumPlace());

        // Mettre à jour le client si nécessaire
        if (reservationDTO.getClientId() != null &&
                !reservationDTO.getClientId().equals(existingReservation.getClient().getId())) {
            Client client = clientRepository.findById(reservationDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID : " + reservationDTO.getClientId()));
            existingReservation.setClient(client);
        }

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return convertToDTO(updatedReservation);
    }

    // Supprimer une réservation
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Réservation non trouvée avec l'ID : " + id);
        }
        reservationRepository.deleteById(id);
    }

    // Convertir Reservation en ReservationDTO
    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDate(reservation.getDate());
        dto.setNumPlace(reservation.getNumPlace());
        dto.setClientId(reservation.getClient().getId());
        dto.setSeanceId(reservation.getSeance().getId());

        // Informations supplémentaires pour l'affichage
        dto.setClientNom(reservation.getClient().getNomComplet());
        dto.setSeanceDateHeure(reservation.getSeance().getDateHeure());
        dto.setFilmTitre(reservation.getSeance().getFilm().getTitre());
        dto.setSalleNom(reservation.getSeance().getSalle().getNom());

        return dto;
    }
}