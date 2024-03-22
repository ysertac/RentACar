package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.*;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    GetFuelsResponse findAll();

    GetFuelResponse findById(long id);

    UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest, long id);

    DeletedFuelResponse delete(long id);
}
