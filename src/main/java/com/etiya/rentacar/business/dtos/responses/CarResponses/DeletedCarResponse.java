package com.etiya.rentacar.business.dtos.responses.CarResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedCarResponse {
    private long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private int modelYear;
    private int state;
    private String plate;
    private double dailyPrice;
    private String modelName;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
