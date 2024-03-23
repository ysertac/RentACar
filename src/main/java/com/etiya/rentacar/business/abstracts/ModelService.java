package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import com.etiya.rentacar.entities.Model;

import java.util.List;

public interface ModelService {
    Model findByName(String name);
    GetModelResponse findById(long id);
    GetModelsResponse findAll();
    CreatedModelResponse add(CreateModelRequest createModelRequest);
    UpdatedModelResponse update(UpdateModelRequest updateModelRequest, long id);
    DeletedModelResponse delete(long id);
}
