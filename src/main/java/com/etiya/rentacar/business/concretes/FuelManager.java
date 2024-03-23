package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.*;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.entities.Fuel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Fuel> allFuels = fuelRepository.findAll();
        GetFuelsResponse getFuelsResponse = new GetFuelsResponse();
        List<String> fuelNames = new ArrayList<>();
        for (Fuel f : allFuels) {
            fuelNames.add(f.getName());
        }
        getFuelsResponse.setFuels(fuelNames);
        return getFuelsResponse;
    }

    @Override
    public GetFuelResponse findById(long id) {
        Optional<Fuel> foundFuel = fuelRepository.findById(id);
        GetFuelResponse getFuelResponse = new GetFuelResponse();
        getFuelResponse.setId(foundFuel.get().getId());
        getFuelResponse.setName(foundFuel.get().getName());
        getFuelResponse.setCreatedTime(foundFuel.get().getCreatedDate());
        getFuelResponse.setUpdatedTime(foundFuel.get().getUpdatedDate());
        getFuelResponse.setDeletedTime(foundFuel.get().getDeletedDate());
        return getFuelResponse;
    }

    @Override
    public Fuel findByName(String name) {
        return fuelRepository.findByName(name).get(0);
    }

    @Override
    public UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id) {
        Optional<Fuel> foundFuel = fuelRepository.findById(id);
        foundFuel.get().setId(id);
        foundFuel.get().setName(updateFuelRequest.getName());
        foundFuel.get().setUpdatedDate(LocalDateTime.now());

        UpdatedFuelResponse updatedFuelResponse = new UpdatedFuelResponse();
        updatedFuelResponse.setId(foundFuel.get().getId());
        updatedFuelResponse.setName(foundFuel.get().getName());
        updatedFuelResponse.setCreateDate(foundFuel.get().getCreatedDate());
        updatedFuelResponse.setUpdateDate(foundFuel.get().getUpdatedDate());

        fuelRepository.save(foundFuel.get());
        return updatedFuelResponse;
    }

    @Override
    public DeletedFuelResponse delete(long id) {
        Optional<Fuel> foundFuel = fuelRepository.findById(id);
        DeletedFuelResponse deletedFuelResponse = new DeletedFuelResponse();
        deletedFuelResponse.setId(foundFuel.get().getId());
        deletedFuelResponse.setName(foundFuel.get().getName());
        deletedFuelResponse.setCreatedDate(foundFuel.get().getCreatedDate());
        deletedFuelResponse.setUpdatedDate(foundFuel.get().getUpdatedDate());
        deletedFuelResponse.setDeletedDate(LocalDateTime.now());

        fuelRepository.delete(foundFuel.get());
        return deletedFuelResponse;
    }
}
