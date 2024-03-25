package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.CreatedFuelResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.DeletedFuelResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.GetFuelsResponse;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.UpdatedFuelResponse;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.entities.Fuel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private ModelMapperService modelMapperService;

    public FuelManager(FuelRepository fuelRepository, ModelMapperService modelMapperService) {
        this.fuelRepository = fuelRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = fuelRepository.save(fuel);

        CreatedFuelResponse createdFuelResponse = modelMapperService.forResponse()
                .map(createdFuel, CreatedFuelResponse.class);

        return createdFuelResponse;
    }

    @Override
    public List<GetFuelsResponse> findAll() {
        List<Fuel> allFuels = fuelRepository.findAll();
        List<GetFuelsResponse> getFuelsResponses = allFuels.stream().map(fuel -> modelMapperService.forResponse()
                .map(fuel, GetFuelsResponse.class)).collect(Collectors.toList());
        return getFuelsResponses;
    }

    @Override
    public GetFuelsResponse findById(long id) {
        Fuel foundFuel = fuelRepository.findById(id).orElse(null);
        GetFuelsResponse getFuelsResponse = modelMapperService.forResponse()
                .map(foundFuel, GetFuelsResponse.class);
        return getFuelsResponse;
    }

    @Override
    public UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id) throws Exception {
        Fuel foundFuel = fuelRepository.findById(id).orElse(null);
        if (foundFuel != null){
            modelMapperService.forRequest().map(updateFuelRequest, foundFuel);
            foundFuel.setUpdatedDate(LocalDateTime.now());
            Fuel updatedFuel = fuelRepository.save(foundFuel);

            return modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);
        } else {
            throw new Exception("There is no fuel with this id");
        }
    }

    @Override
    public DeletedFuelResponse delete(long id) {
        Fuel foundFuel = fuelRepository.findById(id).orElse(null);
        foundFuel.setDeletedDate(LocalDateTime.now());
        DeletedFuelResponse deletedFuelResponse = modelMapperService.forResponse()
                .map(foundFuel, DeletedFuelResponse.class);
        fuelRepository.delete(foundFuel);
        return deletedFuelResponse;
    }
}
