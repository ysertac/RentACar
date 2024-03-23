package com.etiya.rentacar.business.dtos.responses.ModelResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedModelResponse {
    private long id;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
