package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
