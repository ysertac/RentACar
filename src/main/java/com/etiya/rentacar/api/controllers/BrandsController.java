package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.dtos.requests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import com.etiya.rentacar.entities.Brand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandsController {
    private BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetBrandsResponse findAll() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetBrandResponse findById(@PathVariable long id) {
        return brandService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedBrandResponse update(@PathVariable long id, @RequestBody UpdateBrandRequest updateBrandRequest) {

        return brandService.update(updateBrandRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedBrandResponse delete(@PathVariable long id) {
        return brandService.delete(id);
    }
}
