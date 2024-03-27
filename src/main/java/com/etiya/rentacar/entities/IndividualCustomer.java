package com.etiya.rentacar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "individualCustomer")
public class IndividualCustomer extends User {
    @OneToMany(mappedBy = "individualCustomer")
    private List<Rental> rentals;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "identificationNumber")
    private String identificationNumber;
}
