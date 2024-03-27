package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.CityService;
import com.etiya.rentacar.business.dtos.requests.cityRequests.CreateCityRequest;
import com.etiya.rentacar.business.dtos.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentacar.business.dtos.responses.cityResponse.CreatedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.DeletedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.GetCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.UpdatedCityResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cities")
public class CitiesController {
    private CityService cityService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCityResponse findById(@PathVariable int id){
        return cityService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCityResponse> findAll() {
        return cityService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCityResponse update(@PathVariable long id,@Valid @RequestBody UpdateCityRequest updateCityRequest){
        return cityService.update(id, updateCityRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedCityResponse delete(@PathVariable long id) {
        return cityService.delete(id);
    }
}