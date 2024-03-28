package com.etiya.rentacar.business.dtos.requests.transmissionRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransmissionRequest {
    @NotEmpty
    @NotBlank
    private String name;
}
