package com.etiya.rentacar.business.dtos.responses.TransmissionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedTransmissionResponse {
    private long id;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;
}
