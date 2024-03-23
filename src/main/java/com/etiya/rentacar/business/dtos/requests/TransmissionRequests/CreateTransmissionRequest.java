package com.etiya.rentacar.business.dtos.requests.TransmissionRequests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransmissionRequest {
    @NotNull
    @Size(min = 2, max = 15)
    private String name;
}
