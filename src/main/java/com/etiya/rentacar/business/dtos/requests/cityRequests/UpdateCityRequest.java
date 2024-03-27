package com.etiya.rentacar.business.dtos.requests.cityRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 15)
    private String name;
}
