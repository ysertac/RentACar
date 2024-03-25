package com.etiya.rentacar.business.dtos.responses.CarResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCarResponse {
    private long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private int modelYear;
    private int state;
    private String plate;
    private double dailyPrice;
    private long modelId;
    private long brandId;
    private long fuelId;
    private long transmissionId;
}
