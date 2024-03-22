package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.entities.Fuel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;

    public FuelManager(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = new Fuel();
        fuel.setName(createFuelRequest.getName());
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = fuelRepository.save(fuel);

        CreatedFuelResponse createdFuelResponse = new CreatedFuelResponse();
        createdFuelResponse.setId(createdFuel.getId());
        createdFuelResponse.setName(createdFuel.getName());
        createdFuelResponse.setCreateDate(createdFuel.getCreatedDate());
        return createdFuelResponse;
    }


    @Override
    public GetFuelsResponse findAll() {
        return null;
    }

    @Override
    public GetFuelResponse findById(long id) {
        return null;
    }

    @Override
    public UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id) {
        return null;
    }

    @Override
    public DeletedFuelResponse delete(long id) {
        return null;
    }
}
