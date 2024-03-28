package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.CreateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.UpdateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.CreatedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.DeletedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.GetRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.UpdatedRentalBranchResponse;

import java.util.List;

public interface RentalBranchService {
    CreatedRentalBranchResponse add(CreateRentalBranchRequest createRentalBranchRequest);

    List<GetRentalBranchResponse> findAll();

    GetRentalBranchResponse findById(long id);

    UpdatedRentalBranchResponse update(UpdateRentalBranchRequest updateRentalBranchRequest, long id);

    DeletedRentalBranchResponse delete(long id);
}
