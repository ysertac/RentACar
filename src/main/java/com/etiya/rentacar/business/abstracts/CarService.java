package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CreatedCarResponse;
import com.etiya.rentacar.business.dtos.responses.GetCarResponse;
import com.etiya.rentacar.business.dtos.responses.GetCarsResponse;

public interface CarService {
    GetCarsResponse findAll();
    GetCarResponse findById(long id);
    CreatedCarResponse add(CreateCarRequest createCarRequest);
}
