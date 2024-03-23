package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    private TransmissionService transmissionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetTransmissionResponse findById(@PathVariable long id) {
        return transmissionService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetTransmissionsResponse findAll() {
        return transmissionService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@RequestBody CreateTransmissionRequest createTransmissionRequest) {
        return transmissionService.add(createTransmissionRequest);
    }

    @PutMapping("/{id}")
    public UpdatedTransmissionResponse update(@PathVariable long id,
                                              @RequestBody UpdateTransmissionRequest updateTransmissionRequest) {
        return transmissionService.update(updateTransmissionRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedTransmissionResponse delete(@PathVariable long id) {
        return transmissionService.delete(id);
    }
}
