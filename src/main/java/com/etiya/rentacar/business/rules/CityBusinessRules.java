package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.CityRepository;
import com.etiya.rentacar.entities.Car;
import com.etiya.rentacar.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityBusinessRules {
    private CityRepository cityRepository;

    public void cityNameCannotBeDuplicated(String cityName) {
        Optional<City> foundCity = cityRepository.findByNameIgnoreCase(cityName);

        if (foundCity.isPresent() && foundCity.get().getDeletedDate() == null) {
            throw new BusinessException("This city already exists");
        }
    }

    public void cityNotFound(long id) {
        Optional<City> foundCar = cityRepository.findById(id);

        if (!foundCar.isPresent()) {
            throw new BusinessException("There is no car with this id");
        }
    }

    public void deletedCity(long id) {
        City foundCar = cityRepository.findById(id).orElse(null);

        if (foundCar.getDeletedDate() != null) {
            throw new BusinessException("This car is deleted");
        }
    }
}
