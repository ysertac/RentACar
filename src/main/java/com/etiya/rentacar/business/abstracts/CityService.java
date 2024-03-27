package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.cityRequests.CreateCityRequest;
import com.etiya.rentacar.business.dtos.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentacar.business.dtos.responses.cityResponse.CreatedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.DeletedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.GetCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.UpdatedCityResponse;

import java.util.List;

public interface CityService {
    CreatedCityResponse add(CreateCityRequest createCityRequest);

    List<GetCityResponse> findAll();

    GetCityResponse findById(long id);

    UpdatedCityResponse update(long id, UpdateCityRequest updateCityRequest);

    DeletedCityResponse delete(long id);
}
