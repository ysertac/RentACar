package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.CarRepository;
import com.etiya.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;

    public void carPlateCannotBeDuplicated(String plate) {
        Optional<Car> car = carRepository.findByPlate(plate);

        if (car.isPresent() && car.get().getDeletedDate() == null){
            throw new BusinessException("Car with this plate already exists");
        }
    }

    public void carNotFound(long id) {
        Optional<Car> foundCar = carRepository.findById(id);

        if (!foundCar.isPresent()) {
            throw new BusinessException("There is no car with this id");
        }
    }

    public void deletedCar(long id) {
        Car foundCar = carRepository.findById(id).orElse(null);

        if (foundCar.getDeletedDate() != null) {
            throw new BusinessException("This car is deleted");
        }
    }
}
