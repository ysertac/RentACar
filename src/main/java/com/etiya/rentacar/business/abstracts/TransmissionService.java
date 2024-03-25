package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.*;
import com.etiya.rentacar.entities.Transmission;

import java.util.List;

public interface TransmissionService {
    GetTransmissionsResponse findById(long id);

    List<GetTransmissionsResponse> findAll();

    Transmission findByName(String name);

    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id) throws Exception;

    DeletedTransmissionResponse delete(long id);
}
