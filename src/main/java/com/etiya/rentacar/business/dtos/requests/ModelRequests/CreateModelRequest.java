package com.etiya.rentacar.business.dtos.requests.ModelRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
    private String name;
    private long fuelId;
    private long transmissionId;
    private long brandID;
}
