package org.example.backend.Services;

import org.example.backend.dto.LoginRequest;
import org.example.backend.dto.LoginResponse;
import org.example.backend.entity.Admin;
import org.example.backend.entity.Client;
import org.example.backend.entity.Role;
import org.example.backend.repository.AdminRepository;
import org.example.backend.repository.ClientRepository;
import org.example.backend.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;



    @Test
    public void testLoginWithValidAdminCredentials() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setModPass("admin123");
        admin.setNomComplet("Admin Test");
        admin.setRole(Role.ADMIN);

        adminRepository.save(admin);

        LoginRequest request = new LoginRequest("admin@example.com", "admin123");
        LoginResponse response = authService.login(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Connexion réussie", response.getMessage());
        Assertions.assertEquals("admin@example.com", response.getEmail());
        Assertions.assertEquals(Role.ADMIN.name(), response.getRole().name());

    }
    @Test
    public void testLoginWithValidClientCredentials() {
        Client client = new Client();
        client.setEmail("client@example.com");
        client.setModPass("client123");
        client.setNomComplet("Client Test");
        client.setRole(Role.CLIENT);

        clientRepository.save(client);

        LoginRequest request = new LoginRequest("client@example.com", "client123");
        LoginResponse response = authService.login(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Connexion réussie", response.getMessage());
        Assertions.assertEquals("client@example.com", response.getEmail());
        Assertions.assertEquals(Role.CLIENT.name(), response.getRole().name());
    }

}
