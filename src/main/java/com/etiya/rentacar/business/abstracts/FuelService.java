package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.*;
import com.etiya.rentacar.entities.Fuel;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    List<GetFuelsResponse> findAll();

    GetFuelsResponse findById(long id);

    UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id);

    DeletedFuelResponse delete(long id);
}
