package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.*;
import com.etiya.rentacar.entities.Fuel;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    GetFuelsResponse findAll();

    GetFuelResponse findById(long id);

    Fuel findByName(String name);

    UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id);

    DeletedFuelResponse delete(long id);
}
