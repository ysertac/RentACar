package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.CreatedCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.GetCarResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.GetCarsResponse;
import com.etiya.rentacar.business.dtos.responses.CarResponses.UpdatedCarResponse;

public interface CarService {
    GetCarsResponse findAll();
    GetCarResponse findById(long id);
    CreatedCarResponse add(CreateCarRequest createCarRequest);
    UpdatedCarResponse update(UpdateCarRequest updateCarRequest, long id);
}
