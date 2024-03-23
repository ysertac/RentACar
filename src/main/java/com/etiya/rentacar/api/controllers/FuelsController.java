package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
public class FuelsController {
    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest createFuelRequest) {
        return fuelService.add(createFuelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetFuelsResponse findAll() {
        return fuelService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetFuelResponse findById(@PathVariable long id) {
        return fuelService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedFuelResponse update(@PathVariable long id, @RequestBody UpdateFuelRequest updateFuelRequest) {
        return fuelService.update(updateFuelRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedFuelResponse delete(@PathVariable long id) {
        return fuelService.delete(id);
    }
}
