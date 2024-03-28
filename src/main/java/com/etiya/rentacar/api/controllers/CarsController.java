package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.dtos.requests.carRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.carRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.carResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
public class CarsController {
    private CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCarsResponse> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCarsResponse findById(@PathVariable long id) {
        return carService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse add(@Valid @RequestBody CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCarResponse update(@Valid @RequestBody UpdateCarRequest updateCarRequest, @PathVariable long id) {
        return carService.update(updateCarRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedCarResponse delete(@PathVariable long id) {
        return carService.delete(id);
    }
}
