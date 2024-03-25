package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.ModelRequests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.ModelRequests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.ModelResponses.*;
import com.etiya.rentacar.entities.Model;

import java.util.List;

public interface ModelService {
    Model findByName(String name);
    GetModelsResponse findById(long id);
    List<GetModelsResponse> findAll();
    CreatedModelResponse add(CreateModelRequest createModelRequest);
    UpdatedModelResponse update(UpdateModelRequest updateModelRequest, long id) throws Exception;
    DeletedModelResponse delete(long id);
}
