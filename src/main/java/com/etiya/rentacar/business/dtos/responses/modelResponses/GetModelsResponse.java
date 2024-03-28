package com.etiya.rentacar.business.dtos.responses.modelResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelsResponse {
    private long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private long brandId;
    private long fuelId;
    private long transmissionId;
}
