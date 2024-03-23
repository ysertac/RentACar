package com.etiya.rentacar.business.dtos.responses.ModelResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedModelResponse {
    private long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
