package org.example.backend.repository;

import org.example.backend.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalleRepository extends JpaRepository<Salle, Long> {


    // count salle
    @Query(value = "select count(*) from salle",nativeQuery = true)
    int countSalles();
}
