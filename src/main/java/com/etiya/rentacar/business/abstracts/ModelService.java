package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.responses.CreatedModelResponse;
import com.etiya.rentacar.business.dtos.responses.GetModelResponse;
import com.etiya.rentacar.business.dtos.responses.GetModelsResponse;

public interface ModelService {
    GetModelResponse findById(long id);
    GetModelsResponse findAll();
    CreatedModelResponse add(CreateModelRequest createModelRequest);
}
