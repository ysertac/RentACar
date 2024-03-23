package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.*;
import com.etiya.rentacar.entities.Transmission;

public interface TransmissionService {
    GetTransmissionResponse findById(long id);

    GetTransmissionsResponse findAll();

    Transmission findByName(String name);

    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id);

    DeletedTransmissionResponse delete(long id);
}
