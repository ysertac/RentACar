package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.CreatedRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.DeletedRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.GetRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.UpdatedRentalResponse;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse add(CreateRentalRequest createRentalRequest);

    List<GetRentalResponse> findAll();

    GetRentalResponse findById(long id);

    UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest, long id);

    DeletedRentalResponse delete(long id);
}
