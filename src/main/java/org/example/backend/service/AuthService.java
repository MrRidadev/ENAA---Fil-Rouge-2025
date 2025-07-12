package org.example.backend.service;

import org.example.backend.dto.LoginRequest;
import org.example.backend.dto.LoginResponse;
import org.example.backend.entity.Admin;
import org.example.backend.entity.Client;
import org.example.backend.repository.AdminRepository;
import org.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String motDePasse = loginRequest.getMotDePasse();

        // Vérifier d'abord dans la table Admin
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getModPass().equals(motDePasse)) {
                return new LoginResponse(
                        "Connexion réussie",
                        true,
                        admin.getId(),
                        admin.getNomComplet(),
                        admin.getEmail(),
                        admin.getRole()
                );
            }
        }

        // Vérifier ensuite dans la table Client
        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (client.getModPass().equals(motDePasse)) {
                return new LoginResponse(
                        "Connexion réussie",
                        true,
                        client.getId(),
                        client.getNomComplet(),
                        client.getEmail(),
                        client.getRole()
                );
            }
        }

        // Si aucune correspondance trouvée ou mot de passe incorrect
        return new LoginResponse("Email ou mot de passe incorrect", false);
    }
}
