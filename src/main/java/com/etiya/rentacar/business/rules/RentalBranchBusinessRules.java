package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.business.messages.RentalBranchMessages;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.RentalBranchRepository;
import com.etiya.rentacar.entities.Brand;
import com.etiya.rentacar.entities.RentalBranch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBranchBusinessRules {
    private RentalBranchRepository rentalBranchRepository;

    public void rentalBranchNameCannotBeDuplicated(String brandName) {
        Optional<RentalBranch> foundRentalBranch = rentalBranchRepository.findByNameIgnoreCase(brandName);

        if (foundRentalBranch.isPresent() && foundRentalBranch.get().getDeletedDate() == null) {
            throw new BusinessException(RentalBranchMessages.rentalBranchNameCannotBeDuplicated);
        }
    }

    public void rentalBranchNotFound(long brandId) {
        Optional<RentalBranch> foundRentalBranch = rentalBranchRepository.findById(brandId);

        if (!foundRentalBranch.isPresent()) {
            throw new BusinessException(RentalBranchMessages.rentalBranchNotFound);
        }
    }

    public void deletedRentalBranch(long id) {
        RentalBranch foundRentalBranch = rentalBranchRepository.findById(id).orElse(null);

        if (foundRentalBranch.getDeletedDate() != null) {
            throw new BusinessException(RentalBranchMessages.deletedRentalBranch);
        }
    }
}
