package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.*;

import java.util.List;

public interface CarService {
    List<GetCarsResponse> findAll();
    GetCarsResponse findById(long id);
    CreatedCarResponse add(CreateCarRequest createCarRequest);
    UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id) throws Exception;
    DeletedCarResponse delete(long id);
}
