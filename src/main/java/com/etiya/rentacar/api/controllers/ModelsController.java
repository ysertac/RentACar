package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.dtos.requests.modelRequests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.modelRequests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.modelResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelsController {
    private ModelService modelService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelsResponse findById(@PathVariable long id) {
        return modelService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetModelsResponse> findAll() {
        return modelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@Valid @RequestBody CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedModelResponse update(@Valid @RequestBody UpdateModelRequest updateModelRequest,
                                       @PathVariable long id) {
        return modelService.update(updateModelRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedModelResponse delete(@PathVariable long id) {
        return modelService.delete(id);
    }
}
