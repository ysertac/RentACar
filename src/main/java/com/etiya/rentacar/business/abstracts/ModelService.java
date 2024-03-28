package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.modelRequests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.modelRequests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.modelResponses.*;

import java.util.List;

public interface ModelService {
    GetModelsResponse findById(long id);
    List<GetModelsResponse> findAll();
    CreatedModelResponse add(CreateModelRequest createModelRequest);
    UpdatedModelResponse update(UpdateModelRequest updateModelRequest, long id);
    DeletedModelResponse delete(long id);
}
