package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    List<Fuel> findByName(String name);
}
