package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    List<Transmission> findByName(String name);
}
