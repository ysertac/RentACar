package com.etiya.rentacar.business.dtos.responses.CarResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarsResponse {
    private List<String> carNames;
}
