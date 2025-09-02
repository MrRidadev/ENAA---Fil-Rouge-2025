package org.example.backend.service;

import org.example.backend.config.JwtUtil;
import org.example.backend.dto.LoginRequest;
import org.example.backend.dto.LoginResponse;
import org.example.backend.entity.Admin;
import org.example.backend.entity.Client;
import org.example.backend.repository.AdminRepository;
import org.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String motDePasse = loginRequest.getMotDePasse();

        // Vérifier d'abord dans la table Admin
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Pour le moment, on garde la comparaison simple, mais vous devriez hasher les mots de passe
            if (admin.getModPass().equals(motDePasse)) {
                String token = jwtUtil.generateToken(admin.getEmail(), admin.getRole().name(), admin.getId());

                LoginResponse response = new LoginResponse(
                        "Connexion réussie",
                        true,
                        admin.getId(),
                        admin.getNomComplet(),
                        admin.getEmail(),
                        admin.getRole()
                );
                response.setToken(token);
                return response;
            }
        }

        // Vérifier ensuite dans la table Client
        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (client.getModPass().equals(motDePasse)) {
                String token = jwtUtil.generateToken(client.getEmail(), client.getRole().name(), client.getId());

                LoginResponse response = new LoginResponse(
                        "Connexion réussie",
                        true,
                        client.getId(),
                        client.getNomComplet(),
                        client.getEmail(),
                        client.getRole()
                );
                response.setToken(token);
                return response;
            }
        }

        // Si aucune correspondance trouvée ou mot de passe incorrect
        return new LoginResponse("Email ou mot de passe incorrect", false);
    }

    // Méthode pour hasher les mots de passe (à utiliser lors de l'enregistrement)
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Méthode pour vérifier un mot de passe hashé
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}