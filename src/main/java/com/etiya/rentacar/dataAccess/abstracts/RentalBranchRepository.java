package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.RentalBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalBranchRepository extends JpaRepository<RentalBranch, Long> {
    Optional<RentalBranch> findByNameIgnoreCase(String name);
}
