package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    Optional<Transmission> findByNameIgnoreCase(String name);
}
