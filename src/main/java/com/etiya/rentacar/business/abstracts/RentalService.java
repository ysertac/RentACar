package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.ReturnRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.*;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse add(CreateRentalRequest createRentalRequest);

    List<GetRentalResponse> findAll();

    GetRentalResponse findById(long id);

    UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest, long id);

    DeletedRentalResponse delete(long id);

    ReturnedRentalResponse returnRental(ReturnRentalRequest returnRentalRequest, long id);
}
