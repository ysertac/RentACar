package com.etiya.rentacar.business.dtos.requests.rentalBranchRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalBranchRequest {
    private String name;
    private long cityId;
}
