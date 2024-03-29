package com.etiya.rentacar.business.dtos.requests.transmissionRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransmissionRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
}
