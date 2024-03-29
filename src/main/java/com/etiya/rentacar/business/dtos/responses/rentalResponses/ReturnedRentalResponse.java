package com.etiya.rentacar.business.dtos.responses.rentalResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedRentalResponse {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime returnDate;
    private long startKilometer;
    private long endKilometer;
    private long carId;
    private long customerId;
}
