package com.etiya.rentacar.business.dtos.requests.rentalRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRentalRequest {
    @NotNull
    private int endKilometer;
    @NotNull
    @Min(1)
    private int rentalBranchId;
}
