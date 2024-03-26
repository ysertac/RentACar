package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNameIgnoreCase(String brandName);
}
