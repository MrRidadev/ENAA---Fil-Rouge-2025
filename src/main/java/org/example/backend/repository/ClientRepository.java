package org.example.backend.repository;

import org.example.backend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository   extends JpaRepository<Client, Long> {
}
