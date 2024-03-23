package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.*;

public interface TransmissionService {
    GetTransmissionResponse findById(long id);

    GetTransmissionsResponse findAll();

    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id);

    DeletedTransmissionResponse delete(long id);
}
