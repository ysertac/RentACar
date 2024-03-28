package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.transmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.transmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.transmissionResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    private TransmissionService transmissionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTransmissionsResponse findById(@PathVariable long id) {
        return transmissionService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetTransmissionsResponse> findAll() {
        return transmissionService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@Valid @RequestBody CreateTransmissionRequest createTransmissionRequest) {
        return transmissionService.add(createTransmissionRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedTransmissionResponse update(@Valid @PathVariable long id,
                                              @RequestBody UpdateTransmissionRequest updateTransmissionRequest) {
        return transmissionService.update(updateTransmissionRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedTransmissionResponse delete(@PathVariable long id) {
        return transmissionService.delete(id);
    }
}
