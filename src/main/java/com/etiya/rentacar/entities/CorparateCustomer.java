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
@Table(name = "corporateCustomer")
public class CorparateCustomer extends User {
    @OneToMany(mappedBy = "corporateCustomer")
    private List<Rental> rentals;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "taxNumber")
    private String taxNumber;
}
