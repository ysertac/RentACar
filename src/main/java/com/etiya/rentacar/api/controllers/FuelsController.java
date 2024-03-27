package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.CreateFuelRequest;
import com.etiya.rentacar.business.dtos.requests.FuelRequests.UpdateFuelRequest;
import com.etiya.rentacar.business.dtos.responses.FuelResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    public List<GetFuelsResponse> findAll() {
        return fuelService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFuelsResponse findById(@PathVariable long id) {
        return fuelService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedFuelResponse update(@Valid @PathVariable long id,
                                      @RequestBody UpdateFuelRequest updateFuelRequest) {
        return fuelService.update(updateFuelRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedFuelResponse delete(@PathVariable long id) {
        return fuelService.delete(id);
    }
}
