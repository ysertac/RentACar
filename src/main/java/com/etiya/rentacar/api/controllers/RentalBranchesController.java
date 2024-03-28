package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.RentalBranchService;
import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.CreateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.UpdateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.CreatedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.DeletedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.GetRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.UpdatedRentalBranchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentalbranches")
public class RentalBranchesController {
    private RentalBranchService rentalBranchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalBranchResponse add(@RequestBody CreateRentalBranchRequest createRentalBranchRequest) {
        return rentalBranchService.add(createRentalBranchRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetRentalBranchResponse> findAll() {
        return rentalBranchService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetRentalBranchResponse findById(@PathVariable long id) {
        return rentalBranchService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedRentalBranchResponse update(@RequestBody UpdateRentalBranchRequest updateRentalBranchRequest,@PathVariable long id) {
        return rentalBranchService.update(updateRentalBranchRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedRentalBranchResponse delete(@PathVariable long id) {
        return rentalBranchService.delete(id);
    }
}
