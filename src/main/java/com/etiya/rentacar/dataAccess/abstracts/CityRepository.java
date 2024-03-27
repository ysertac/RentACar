package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameIgnoreCase(String cityName);
}
