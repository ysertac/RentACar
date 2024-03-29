package com.etiya.rentacar.business.dtos.requests.rentalRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @NotNull
    @Min(1)
    private long carId;
    @NotNull
    @Min(1)
    private long customerId;
    @NotEmpty
    @NotBlank
    @FutureOrPresent
    private LocalDateTime StartDate;
    @NotEmpty
    @NotBlank
    @FutureOrPresent
    private LocalDateTime EndDate;
}
