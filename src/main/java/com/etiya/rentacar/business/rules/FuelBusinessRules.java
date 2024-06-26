package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.business.messages.FuelMessages;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.entities.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelBusinessRules {
    private FuelRepository fuelRepository;

    public void fuelNameCannotBeDublicated(String fuelName) {
        Optional<Fuel> fuel = fuelRepository.findByNameIgnoreCase(fuelName);
        if (fuel.isPresent() && fuel.get().getDeletedDate() == null) {
            throw new BusinessException(FuelMessages.fuelNameCannotBeDuplicated);
        }
    }

    public void fuelNotFound(long fuelId) {
        Optional<Fuel> foundBrand = fuelRepository.findById(fuelId);

        if (!foundBrand.isPresent()) {
            throw new BusinessException(FuelMessages.fuelNotFound);
        }
    }

    public void deletedFuel(long fuelId) {
        Fuel foundFuel = fuelRepository.findById(fuelId).orElse(null);

        if (foundFuel.getDeletedDate() != null) {
            throw new BusinessException(FuelMessages.deletedFuel);
        }
    }
}