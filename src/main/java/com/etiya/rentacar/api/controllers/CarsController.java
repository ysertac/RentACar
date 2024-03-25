package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.dtos.requests.CarRequests.CreateCarRequest;
import com.etiya.rentacar.business.dtos.requests.CarRequests.UpdateCarRequest;
import com.etiya.rentacar.business.dtos.responses.CarResponses.*;
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
    @ResponseStatus(HttpStatus.FOUND)
    public List<GetCarsResponse> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetCarsResponse findById(@PathVariable long id) {
        return carService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse add(@RequestBody CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }

    @PutMapping("/{id}")
    public UpdatedCarResponse update(@RequestBody UpdateCarRequest updateCarRequest,
                                     @PathVariable long id) throws Exception {
        return carService.update(updateCarRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedCarResponse delete(@PathVariable long id) {
        return carService.delete(id);
    }
}
