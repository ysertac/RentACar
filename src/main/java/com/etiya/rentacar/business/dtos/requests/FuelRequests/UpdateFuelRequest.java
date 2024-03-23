package com.etiya.rentacar.business.dtos.requests.FuelRequests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFuelRequest {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
}