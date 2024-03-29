package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCustomerIdAndReturnDateIsNull(long customerId);
}
