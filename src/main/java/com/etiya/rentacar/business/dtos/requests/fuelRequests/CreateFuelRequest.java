package com.etiya.rentacar.business.dtos.requests.fuelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFuelRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
