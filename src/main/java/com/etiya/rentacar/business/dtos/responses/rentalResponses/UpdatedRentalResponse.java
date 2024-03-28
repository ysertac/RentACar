package com.etiya.rentacar.business.dtos.responses.rentalResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedRentalResponse {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long startKilometer;
    private long endKilometer;
    private long carId;
    private long customerId;
}
