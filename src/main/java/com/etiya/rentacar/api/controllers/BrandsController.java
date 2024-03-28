package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.dtos.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.brandResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    public List<GetBrandsResponse> findAll() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBrandsResponse findById(@PathVariable long id) {
        return brandService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBrandResponse update(@Valid @PathVariable long id,
                                       @RequestBody UpdateBrandRequest updateBrandRequest) {

        return brandService.update(updateBrandRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedBrandResponse delete(@PathVariable long id) {
        return brandService.delete(id);
    }
}
