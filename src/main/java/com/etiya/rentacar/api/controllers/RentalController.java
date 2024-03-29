package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.RentalService;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.ReturnRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentacar.business.dtos.responses.brandResponses.DeletedBrandResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentalController {
    private RentalService rentalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetRentalResponse> findAll() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetRentalResponse findById(@PathVariable long id) {
        return rentalService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest) {
        return rentalService.add(createRentalRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest, @PathVariable long id) {
        return rentalService.update(updateRentalRequest, id);
    }

    @PutMapping("return/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReturnedRentalResponse returnRental(@RequestBody ReturnRentalRequest returnRentalRequest, @PathVariable long id) {
        return rentalService.returnRental(returnRentalRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedRentalResponse delete(@PathVariable long id) {
        return rentalService.delete(id);
    }
}
