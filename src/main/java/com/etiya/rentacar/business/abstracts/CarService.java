package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.*;

public interface CarService {
    GetCarsResponse findAll();
    GetCarResponse findById(long id);
    CreatedCarResponse add(CreateCarRequest createCarRequest);
    UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id);
    DeletedCarResponse delete(long id);
}
