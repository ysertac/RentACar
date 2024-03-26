package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findByNameIgnoreCase(String name);
}
