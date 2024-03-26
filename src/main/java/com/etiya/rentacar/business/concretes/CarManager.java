package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.CreatedCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.DeletedCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.GetCarsResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.UpdatedCarResponse;
import com.etiya.rentacar.business.rules.CarBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.CarRepository;
import com.etiya.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private CarBusinessRules carBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetCarsResponse> findAll() {
        List<Car> cars = carRepository.findAll();
        List<GetCarsResponse> getCarsResponses = cars.stream()
                .filter(car -> car.getDeletedDate() == null)
                .map(car ->
                        modelMapperService.forResponse().map(car, GetCarsResponse.class)).collect(Collectors.toList());
        return getCarsResponses;
    }

    @Override
    public GetCarsResponse findById(long id) {
        carBusinessRules.carNotFound(id);
        carBusinessRules.deletedCar(id);

        Car foundCar = carRepository.findById(id).orElse(null);
        GetCarsResponse getCarsResponse = modelMapperService.forResponse()
                .map(foundCar, GetCarsResponse.class);
        return getCarsResponse;
    }

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        carBusinessRules.carPlateCannotBeDuplicated(createCarRequest.getPlate());

        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setId(0);
        Car createdCar = carRepository.save(car);

        CreatedCarResponse createdCarResponse = modelMapperService.forResponse()
                .map(createdCar, CreatedCarResponse.class);

        return createdCarResponse;
    }

    @Override
    public UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id) {
        carBusinessRules.carNotFound(id);
        carBusinessRules.carPlateCannotBeDuplicated(updateCarRequest.getPlate());
        carBusinessRules.deletedCar(id);

        Car foundCar = carRepository.findById(id).orElse(null);
        Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        car.setId(id);
        car.setUpdatedDate(LocalDateTime.now());
        car.setCreatedDate(foundCar.getCreatedDate());
        Car updatedCar = carRepository.save(car);

        UpdatedCarResponse updatedCarResponse = modelMapperService.forResponse()
                .map(updatedCar, UpdatedCarResponse.class);
        return updatedCarResponse;

    }

    @Override
    public DeletedCarResponse delete(long id) {
        carBusinessRules.carNotFound(id);
        carBusinessRules.deletedCar(id);

        Car foundCar = carRepository.findById(id).orElse(null);
        foundCar.setId(id);
        foundCar.setDeletedDate(LocalDateTime.now());
        Car deletedCar = carRepository.save(foundCar);

        DeletedCarResponse deletedCarResponse = modelMapperService.forResponse()
                .map(deletedCar, DeletedCarResponse.class);

        return deletedCarResponse;
    }
}
