package com.etiya.rentacar.business.dtos.requests.ModelRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 15)
    private String name;

    @NotNull
    @Min(1)
    private long fuelId;

    @NotNull
    @Min(1)
    private long transmissionId;

    @NotNull
    @Min(1)
    private long brandID;
}
