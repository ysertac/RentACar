package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.abstracts.CustomerService;
import com.etiya.rentacar.business.dtos.responses.carResponses.GetCarsResponse;
import com.etiya.rentacar.business.messages.RentalMessages;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.RentalRepository;
import com.etiya.rentacar.entities.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;
    private CarService carService;

    public void rentalNotFound(long rentalId) {
        Optional<Rental> foundRental = rentalRepository.findById(rentalId);

        if (!foundRental.isPresent()) {
            throw new BusinessException(RentalMessages.rentalNotFound);
        }
    }

    public void deletedRental(long id) {
        Rental foundRental = rentalRepository.findById(id).orElse(null);

        if (foundRental.getDeletedDate() != null) {
            throw new BusinessException(RentalMessages.deletedRental);
        }
    }

    public void checkIfCarState(long carId){
        GetCarsResponse response = carService.findById(carId);
        if(response.getState()==2)
            throw new BusinessException(RentalMessages.checkIfCarState2);

        if (response.getState()==3)
            throw new BusinessException(RentalMessages.checkIfCarState3);
    }

    public void checkCustomerHasRented(long customerId) {
        List<Rental> activeRentals = rentalRepository.findByCustomerIdAndReturnDateIsNull(customerId);
        if (!activeRentals.isEmpty()) {
            throw new BusinessException(RentalMessages.checkCustomerHasRented);
        }
    }
}
