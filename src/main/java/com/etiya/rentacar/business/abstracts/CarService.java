package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.carRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.carRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.carResponses.*;

import java.util.List;

public interface CarService {
    List<GetCarsResponse> findAll();
    GetCarsResponse findById(long id);
    CreatedCarResponse add(CreateCarRequest createCarRequest);
    UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id);
    DeletedCarResponse delete(long id);
}
