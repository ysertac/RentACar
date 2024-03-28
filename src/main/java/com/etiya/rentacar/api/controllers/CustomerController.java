package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.CustomerService;
import com.etiya.rentacar.business.dtos.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentacar.business.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.rentacar.business.dtos.responses.customerResponses.CreatedCustomerResponses;
import com.etiya.rentacar.business.dtos.responses.customerResponses.DeletedCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.GetCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.UpdatedCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse findById(@PathVariable long id){
        return customerService.findById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerResponse> findAll(){
        return customerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponses add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        return customerService.add(createCustomerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCustomerResponse update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable long id) {
        return customerService.update(updateCustomerRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedCustomerResponse delete(@PathVariable long id) {
        return customerService.delete(id);
    }
}
