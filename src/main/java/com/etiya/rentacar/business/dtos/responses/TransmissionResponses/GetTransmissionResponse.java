package com.etiya.rentacar.business.dtos.responses.TransmissionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTransmissionResponse {
    private long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
}
