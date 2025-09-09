package org.example.backend.repository;

import org.example.backend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository   extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
    @Query(value = "select count(*) from client",nativeQuery = true)
    int countClient();
}
