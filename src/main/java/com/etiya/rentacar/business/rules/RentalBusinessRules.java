package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.RentalRepository;
import com.etiya.rentacar.entities.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;

    public void rentalNotFound(long rentalId) {
        Optional<Rental> foundRental = rentalRepository.findById(rentalId);

        if (!foundRental.isPresent()) {
            throw new BusinessException("There is no rental with this id");
        }
    }

    public void deletedRental(long id) {
        Rental foundRental = rentalRepository.findById(id).orElse(null);

        if (foundRental.getDeletedDate() != null) {
            throw new BusinessException("This rental is deleted");
        }
    }
}
