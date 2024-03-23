package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.dtos.requests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelsController {
    private ModelService modelService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetModelResponse findById(@PathVariable long id) {
        return modelService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetModelsResponse findAll() {
        return modelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@RequestBody CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);
    }

    @PutMapping("/{id}")
    public UpdatedModelResponse update(@RequestBody UpdateModelRequest updateModelRequest, @PathVariable long id) {
        return modelService.update(updateModelRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedModelResponse delete(@PathVariable long id) {
        return modelService.delete(id);
    }
}
