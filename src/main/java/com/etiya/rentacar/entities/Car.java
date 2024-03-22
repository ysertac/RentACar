package com.etiya.rentacar.entities;

import com.etiya.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "state")
    private int state;

    @Column(name = "slate")
    private String slate;

    @Column(name = "engineCapacity")
    private double engineCapacity;

    @Column(name = "horsePower")
    private double horsePower;

    @Column(name = "peopleCapacity")
    private int peopleCapacity;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "modelId")
    private Model model;
}
