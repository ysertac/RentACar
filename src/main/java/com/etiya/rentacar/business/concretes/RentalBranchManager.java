package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.RentalBranchService;
import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.CreateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.requests.rentalBranchRequests.UpdateRentalBranchRequest;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.CreatedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.DeletedRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.GetRentalBranchResponse;
import com.etiya.rentacar.business.dtos.responses.rentalBranchResponses.UpdatedRentalBranchResponse;
import com.etiya.rentacar.business.rules.RentalBranchBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.RentalBranchRepository;
import com.etiya.rentacar.entities.RentalBranch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalBranchManager implements RentalBranchService {
    private RentalBranchRepository rentalBranchRepository;
    private RentalBranchBusinessRules rentalBranchBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedRentalBranchResponse add(CreateRentalBranchRequest createRentalBranchRequest) {
        rentalBranchBusinessRules.rentalBranchNameCannotBeDuplicated(createRentalBranchRequest.getName());

        RentalBranch rentalBranch = modelMapperService.forRequest().map(createRentalBranchRequest, RentalBranch.class);
        rentalBranch.setId(0);
        RentalBranch createdRentalBranch = rentalBranchRepository.save(rentalBranch);


        CreatedRentalBranchResponse createdRentalBranchResponse =
                modelMapperService.forResponse().map(createdRentalBranch, CreatedRentalBranchResponse.class);
        return createdRentalBranchResponse;
    }

    @Override
    public List<GetRentalBranchResponse> findAll() {
        List<RentalBranch> rentalBranches = rentalBranchRepository.findAll();

        List<GetRentalBranchResponse> getRentalBranchResponses = rentalBranches.stream().filter(rentalBranch -> rentalBranch.getDeletedDate() == null)
                .map(rentalBranch -> modelMapperService.forResponse()
                        .map(rentalBranch, GetRentalBranchResponse.class)).collect(Collectors.toList());
        return getRentalBranchResponses;
    }

    @Override
    public GetRentalBranchResponse findById(long id) {
        rentalBranchBusinessRules.rentalBranchNotFound(id);
        rentalBranchBusinessRules.deletedRentalBranch(id);

        RentalBranch foundRentalBranch = rentalBranchRepository.findById(id).orElse(null);
        GetRentalBranchResponse getRentalBranchResponse = modelMapperService.forResponse()
                .map(foundRentalBranch, GetRentalBranchResponse.class);
        return getRentalBranchResponse;
    }

    @Override
    public UpdatedRentalBranchResponse update(UpdateRentalBranchRequest updateRentalBranchRequest, long id) {
        rentalBranchBusinessRules.rentalBranchNotFound(id);
        rentalBranchBusinessRules.rentalBranchNameCannotBeDuplicated(updateRentalBranchRequest.getName());
        rentalBranchBusinessRules.deletedRentalBranch(id);

        RentalBranch foundRentalBranch = rentalBranchRepository.findById(id).orElse(null);
        RentalBranch rentalBranch = modelMapperService.forRequest().map(updateRentalBranchRequest, RentalBranch.class);
        rentalBranch.setId(id);
        rentalBranch.setCreatedDate(foundRentalBranch.getCreatedDate());
        rentalBranch.setUpdatedDate(LocalDateTime.now());
        RentalBranch updatedRentalBranch = rentalBranchRepository.save(rentalBranch);
        UpdatedRentalBranchResponse updatedRentalBranchResponse =
                modelMapperService.forResponse().map(updatedRentalBranch, UpdatedRentalBranchResponse.class);
        return updatedRentalBranchResponse;
    }

    @Override
    public DeletedRentalBranchResponse delete(long id) {
        rentalBranchBusinessRules.rentalBranchNotFound(id);
        rentalBranchBusinessRules.deletedRentalBranch(id);

        RentalBranch foundRentalBranch = rentalBranchRepository.findById(id).orElse(null);
        foundRentalBranch.setId(id);
        foundRentalBranch.setDeletedDate(LocalDateTime.now());
        RentalBranch deletedRentalBranch = rentalBranchRepository.save(foundRentalBranch);
        DeletedRentalBranchResponse deletedRentalBranchResponse =
                modelMapperService.forResponse().map(deletedRentalBranch, DeletedRentalBranchResponse.class);
        return deletedRentalBranchResponse;
    }
}
