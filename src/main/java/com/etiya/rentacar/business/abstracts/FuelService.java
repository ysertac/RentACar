package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.fuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.fuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.fuelResponses.*;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    List<GetFuelsResponse> findAll();

    GetFuelsResponse findById(long id);

    UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id);

    DeletedFuelResponse delete(long id);
}
