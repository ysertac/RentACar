package com.etiya.rentacar.business.dtos.requests.rentalBranchRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalBranchRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Min(1)
    private long cityId;
}
