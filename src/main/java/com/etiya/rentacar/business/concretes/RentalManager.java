package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CarService;
import com.etiya.rentacar.business.abstracts.RentalService;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentacar.business.dtos.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.CreatedRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.DeletedRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.GetRentalResponse;
import com.etiya.rentacar.business.dtos.responses.rentalResponses.UpdatedRentalResponse;
import com.etiya.rentacar.business.rules.RentalBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.RentalRepository;
import com.etiya.rentacar.entities.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private CarService carService;
    private ModelMapperService modelMapperService;
    private RentalBusinessRules rentalBusinessRules;


    @Override
    public GetRentalResponse findById(long id) {
        rentalBusinessRules.rentalNotFound(id);
        rentalBusinessRules.deletedRental(id);

        Optional<Rental> rental = rentalRepository.findById(id);
        GetRentalResponse getRentalResponse = modelMapperService.forResponse().map(rental.get(), GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public List<GetRentalResponse> findAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetRentalResponse> getRentalResponses = rentals.stream()
                .filter(rental -> rental.getDeletedDate() == null)
                .map(rental -> modelMapperService.forResponse().map(rental, GetRentalResponse.class))
                .collect(Collectors.toList());

        return getRentalResponses;
    }

    @Override
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setStartKilometer(carService.findById(createRentalRequest.getCarId()).getKilometer());
        rental.setId(0);
        Rental createdRental = rentalRepository.save(rental);

        CreatedRentalResponse createdRentalResponse = modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
        return createdRentalResponse;
    }

    @Override
    public UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest, long id) {
        rentalBusinessRules.rentalNotFound(id);
        rentalBusinessRules.deletedRental(id);

        Rental foundRental = rentalRepository.findById(id).orElse(null);
        Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        rental.setId(id);
        rental.setCreatedDate(foundRental.getCreatedDate());
        rental.setUpdatedDate(LocalDateTime.now());
        Rental updatedRental = rentalRepository.save(rental);

        UpdatedRentalResponse updatedRentalResponse =
                modelMapperService.forResponse().map(updatedRental, UpdatedRentalResponse.class);
        return updatedRentalResponse;
    }

    @Override
    public DeletedRentalResponse delete(long id) {
        rentalBusinessRules.rentalNotFound(id);
        rentalBusinessRules.deletedRental(id);

        Rental foundRental = rentalRepository.findById(id).orElse(null);
        foundRental.setId(id);
        foundRental.setDeletedDate(LocalDateTime.now());

        Rental deletedRental = rentalRepository.save(foundRental);
        DeletedRentalResponse deletedRentalResponse =
                modelMapperService.forResponse().map(deletedRental, DeletedRentalResponse.class);
        return deletedRentalResponse;
    }
}
