package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.dtos.requests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CreatedCarResponse;
import com.etiya.rentacar.business.dtos.responses.GetCarResponse;
import com.etiya.rentacar.business.dtos.responses.GetCarsResponse;
import com.etiya.rentacar.dataAccess.abstracts.*;
import com.etiya.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelService modelService;

    @Override
    public GetCarsResponse findAll() {
        GetCarsResponse getCarsResponse = new GetCarsResponse();
        List carNames = new ArrayList();
        List<Car> allCars = carRepository.findAll();
        for (Car c : allCars) {
            carNames.add(c.getModelYear() + " " + c.getModel().getBrand().getName() + " " + c.getModel().getName());
        }
        getCarsResponse.setCarNames(carNames);
        return getCarsResponse;
    }

    @Override
    public GetCarResponse findById(long id) {
        Optional<Car> foundCar = carRepository.findById(id);
        GetCarResponse getCarResponse = new GetCarResponse();
        getCarResponse.setId(foundCar.get().getId());
        getCarResponse.setState(foundCar.get().getState());
        getCarResponse.setPlate(foundCar.get().getPlate());
        getCarResponse.setModelYear(foundCar.get().getModelYear());
        getCarResponse.setDailyPrice(foundCar.get().getDailyPrice());
        getCarResponse.setCreatedDate(foundCar.get().getCreatedDate());
        getCarResponse.setUpdatedDate(foundCar.get().getUpdatedDate());
        getCarResponse.setDeletedDate(foundCar.get().getDeletedDate());
        getCarResponse.setModelName(foundCar.get().getModel().getName());
        getCarResponse.setBrandName(foundCar.get().getModel().getBrand().getName());
        getCarResponse.setFuelName(foundCar.get().getModel().getFuel().getName());
        getCarResponse.setTransmissionName(foundCar.get().getModel().getTransmission().getName());
        return getCarResponse;
    }

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        Car car = new Car();
        car.setModelYear(createCarRequest.getModelYear());
        car.setState(createCarRequest.getState());
        car.setPlate(createCarRequest.getPlate());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setModel(modelService.findByName(createCarRequest.getModelName()));
        car.setCreatedDate(LocalDateTime.now());
        Car createdCar = carRepository.save(car);

        CreatedCarResponse createdCarResponse = new CreatedCarResponse();
        createdCarResponse.setId(createdCar.getId());
        createdCarResponse.setModelYear(createdCar.getModelYear());
        createdCarResponse.setState(createdCar.getState());
        createdCarResponse.setPlate(createdCar.getPlate());
        createdCarResponse.setDailyPrice(createdCar.getDailyPrice());
        createdCarResponse.setCreatedDate(createdCar.getCreatedDate());
        createdCarResponse.setModelName(createdCar.getModel().getName());
        createdCarResponse.setBrandName(createdCar.getModel().getBrand().getName());
        createdCarResponse.setFuelName(createdCar.getModel().getFuel().getName());
        createdCarResponse.setTransmissionName(createdCar.getModel().getTransmission().getName());
        return createdCarResponse;
    }
}
