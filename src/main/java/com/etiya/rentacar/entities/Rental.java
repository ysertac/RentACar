package com.etiya.rentacar.entities;

import com.etiya.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends BaseEntity {
    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "startKilometer")
    private long startKilometer;

    @Column(name = "returnKilometer")
    private long endKilometer;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "corporateCustomerId")
    private CorparateCustomer corporateCustomer;

    @ManyToOne
    @JoinColumn(name = "individualCustomerId")
    private IndividualCustomer individualCustomer;
}
