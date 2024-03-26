package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.CreatedFuelResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.DeletedFuelResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.GetFuelsResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.UpdatedFuelResponse;
import com.etiya.rentacar.business.rules.FuelBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.entities.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private FuelBusinessRules fuelBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        fuelBusinessRules.fuelNameCannotBeDublicated(createFuelRequest.getName());

        Fuel fuel = modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        Fuel createdFuel = fuelRepository.save(fuel);

        CreatedFuelResponse createdFuelResponse = modelMapperService.forResponse()
                .map(createdFuel, CreatedFuelResponse.class);

        return createdFuelResponse;
    }

    @Override
    public List<GetFuelsResponse> findAll() {
        List<Fuel> allFuels = fuelRepository.findAll();
        List<GetFuelsResponse> getFuelsResponses = allFuels.stream()
                .filter(fuel -> fuel.getDeletedDate() == null)
                .map(fuel ->
                        modelMapperService.forResponse().map(fuel, GetFuelsResponse.class)).collect(Collectors.toList());
        return getFuelsResponses;
    }

    @Override
    public GetFuelsResponse findById(long id) {
        fuelBusinessRules.fuelNotFound(id);
        fuelBusinessRules.deletedFuel(id);

        Fuel foundFuel = fuelRepository.findById(id).orElse(null);
        GetFuelsResponse getFuelsResponse = modelMapperService.forResponse()
                .map(foundFuel, GetFuelsResponse.class);
        return getFuelsResponse;
    }

    @Override
    public UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id) {
        fuelBusinessRules.fuelNotFound(id);
        fuelBusinessRules.fuelNameCannotBeDublicated(updateFuelRequest.getName());
        fuelBusinessRules.deletedFuel(id);

        Fuel foundFuel = fuelRepository.findById(id).orElse(null);

        modelMapperService.forRequest().map(updateFuelRequest, foundFuel);
        foundFuel.setUpdatedDate(LocalDateTime.now());
        Fuel updatedFuel = fuelRepository.save(foundFuel);

        return modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);

    }

    @Override
    public DeletedFuelResponse delete(long id) {
        fuelBusinessRules.fuelNotFound(id);
        fuelBusinessRules.deletedFuel(id);

        Fuel foundFuel = fuelRepository.findById(id).orElse(null);
        foundFuel.setId(id);
        foundFuel.setDeletedDate(LocalDateTime.now());
        Fuel deletedFuel = fuelRepository.save(foundFuel);
        DeletedFuelResponse deletedFuelResponse = modelMapperService.forResponse()
                .map(deletedFuel, DeletedFuelResponse.class);
        return deletedFuelResponse;
    }
}
