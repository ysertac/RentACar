package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.CreatedCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.DeletedCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.GetCarsResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.UpdatedCarResponse;
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
    private ModelMapperService modelMapperService;
    private ModelService modelService;

    @Override
    public List<GetCarsResponse> findAll() {
        List<Car> cars = carRepository.findAll();
        List<GetCarsResponse> getCarsResponses  = cars.stream().map(car ->
                modelMapperService.forResponse()
                        .map(car, GetCarsResponse.class)).collect(Collectors.toList());
        return getCarsResponses;
    }

    @Override
    public GetCarsResponse findById(long id) {
        Car foundCar = carRepository.findById(id).orElse(null);
        GetCarsResponse getCarsResponse = modelMapperService.forResponse()
                .map(foundCar, GetCarsResponse.class);
        return getCarsResponse;
    }

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCreatedDate(LocalDateTime.now());
        Car createdCar =  carRepository.save(car);

        CreatedCarResponse createdCarResponse = modelMapperService.forResponse()
                .map(createdCar,CreatedCarResponse.class);

        return createdCarResponse;
    }

    @Override
    public UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id) throws Exception {
        Car foundCar = carRepository.findById(id).orElse(null);
        if (foundCar != null) {
            Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
            car.setId(id);
            car.setUpdatedDate(LocalDateTime.now());
            car.setCreatedDate(foundCar.getCreatedDate());
            Car updatedCar = carRepository.save(car);
            UpdatedCarResponse updatedCarResponse = modelMapperService.forResponse()
                    .map(updatedCar, UpdatedCarResponse.class);
            return updatedCarResponse;
        } else {
            throw new Exception("There is no car with this id");
        }
    }

    @Override
    public DeletedCarResponse delete(long id) {
        Car foundCar = carRepository.findById(id).orElse(null);

        DeletedCarResponse deletedCarResponse = modelMapperService.forResponse()
                .map(foundCar, DeletedCarResponse.class);

        carRepository.delete(foundCar);
        return deletedCarResponse;
    }
}
