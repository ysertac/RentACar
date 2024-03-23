package com.etiya.rentacar.business.dtos.responses.FuelResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFuelsResponse {
    private List<String> fuels;
}