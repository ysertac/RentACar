package com.etiya.rentacar.business.dtos.requests.CarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int modelYear;
    private int state;
    private String plate;
    private double dailyPrice;
    private long modelId;
}
