package org.example.backend.controller;

import org.example.backend.dto.LoginRequest;
import org.example.backend.dto.LoginResponse;
import org.example.backend.dto.RegisterRequest;
import org.example.backend.entity.Admin;
import org.example.backend.entity.Client;
import org.example.backend.entity.Role;
import org.example.backend.repository.AdminRepository;
import org.example.backend.repository.ClientRepository;
import org.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (request.getRole() == Role.ADMIN) {
            Admin admin = new Admin();
            admin.setNomComplet(request.getNomComplet());
            admin.setEmail(request.getEmail());
            admin.setModPass(request.getModPass());
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
            return ResponseEntity.ok("Admin enregistré avec succès.");
        } else if (request.getRole() == Role.CLIENT) {
            Client client = new Client();
            client.setNomComplet(request.getNomComplet());
            client.setEmail(request.getEmail());
            client.setModPass(request.getModPass());
            client.setRole(Role.CLIENT);
            clientRepository.save(client);
            return ResponseEntity.ok("Client enregistré avec succès.");
        } else {
            return ResponseEntity.badRequest().body("Rôle non reconnu !");
        }
    }

    @GetMapping("/all/{role}")
    public ResponseEntity<?> getAllUsers(@PathVariable("role") Role role) {
        if (role == Role.ADMIN) {
            return ResponseEntity.ok(adminRepository.findAll());
        } else if (role == Role.CLIENT) {
            return ResponseEntity.ok(clientRepository.findAll());
        } else {
            return ResponseEntity.badRequest().body("Rôle non reconnu.");
        }
    }



    @PutMapping("/update/{role}/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("role") Role role,
                                        @PathVariable("id") Long id,
                                        @RequestBody RegisterRequest request) {


        if (role == Role.ADMIN) {
            Optional<Admin> optionalAdmin = adminRepository.findById(id);
            if (optionalAdmin.isPresent()) {
                Admin admin = optionalAdmin.get();
                admin.setNomComplet(request.getNomComplet());
                admin.setEmail(request.getEmail());
                admin.setModPass(request.getModPass());
                adminRepository.save(admin);
                return ResponseEntity.ok("Admin mis à jour.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (role == Role.CLIENT) {
            Optional<Client> optionalClient = clientRepository.findById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                client.setNomComplet(request.getNomComplet());
                client.setEmail(request.getEmail());
                client.setModPass(request.getModPass());
                clientRepository.save(client);
                return ResponseEntity.ok("Client mis à jour.");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().body("Rôle non reconnu.");
    }


}
