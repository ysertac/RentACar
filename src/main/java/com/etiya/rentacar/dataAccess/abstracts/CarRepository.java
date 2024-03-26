package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT * FROM CARS WHERE LOWER(plate)=LOWER(:plate)", nativeQuery = true)
    Optional<Car> findByPlate(String plate);
}
